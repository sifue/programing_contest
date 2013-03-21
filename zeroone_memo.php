<?php
/**
 * 価値と重さのある品物
 */
class Item
{
    /**
     * @var int 重さ
     */
    public $weight = 0;
    /**
     * @var int 価値
     */
    public $value = 0;

    /**
     * @param int $weight
     * @param int $value
     */
    function __construct($weight, $value)
    {
        $this->weight = $weight;
        $this->value = $value;
    }
}

class Memo
{
    private $numberOfItem = 4;
    private $memo = [];
    private $items;

    public function __construct()
    {
        $this->items = [
            new Item(2, 3),
            new Item(1, 2),
            new Item(3, 4),
            new Item(2, 2)
        ];
    }

    // 品物の配列を考え、「i番目以降の品物から総和が$restWeight以下なるもの最大価値」は、
    // 「i + 1 番目以降から$restWeight以下から選んだ時の価値」 と
    // 「i + 1 番目以降から($restWeight - i番目の重さ)以下で選んだ価値 + i番目の価値」
    // のうちの大きいほうの価値が最大価値となる漸化式が立てられる。
    // また、
    // iが4ならばもう価値は生み出さないので価値0が返る
    // i番目の重さが、すでに許容重量より重い場合は、
    // 「i + 1 番目以降から$restWeight以下から選んだ時の価値」と同じ価値となる
    // これを実装すると以下の再帰関数となる

    /**
     * i番目以降の品物から重さの総和がwよりも小さくなるように選ぶ
     * @param int $i
     * @param int $restWeight まだ入れられる許容重量
     * @return int i番目以降の品物から$restWeight以内の重さのものを選んだ時の最大価値
     */
    public function maxValue($i, $restWeight)
    {
        // すでにメモ化していればその値を返す
        if (isset($this->memo[$i.','.$restWeight])) {
            return $this->memo[$i.','.$restWeight];
        }

        if($i === $this->numberOfItem) {
            // もう最期の品物であれば足せる価値はない
            $value = 0;
        } else if ($restWeight < $this->items[$i]->weight) {
            // i番目重さがすでに許容重量より大きいのであれば
            // i + 1 番目のものと同じである
            $value = $this->maxValue($i + 1, $restWeight);
        } else {
            // i番目の品物を選ばなかった時のi +1番目の価値と
            // i番目の品物を選んだ時のi +1番目の価値のうち、大きいほうが最大価値となる
            $value =
                max(
                    $this->maxValue($i + 1, $restWeight),
                    $this->maxValue($i + 1, $restWeight - $this->items[$i]->weight)
                        + $this->items[$i]->value
                );
        }
        // この関数の結果をメモ化して返す
        return $this->memo[$i.','.$restWeight] = $value;
    }
}
echo((new \Memo())->maxValue(0, 5));
