<?php
/**
 * 特定の硬貨1種類を入れることができるコインケース
 */
class CoinCase
{
    /**
     * @var int 入れられる硬貨の種類の値
     */
    public $type = 0;
    /**
     * @var int 硬貨の入っている数
     */
    public $count = 0;

    /**
     * @param $type
     * @param $count
     */
    function __construct($type, $count)
    {
        $this->type = $type;
        $this->count = $count;
    }
}

$coinCases = [
    new CoinCase(1, 3),
    new CoinCase(5, 2),
    new CoinCase(10, 1),
    new CoinCase(50, 3),
    new CoinCase(100, 0),
    new CoinCase(500, 2)
];
$targetValue = 620;
$totalUseCount = 0;
$usedCoinCases = [];

// 大きい硬貨順に並べ替える
$coinCases = array_reverse($coinCases);

foreach ($coinCases as $coinCase) {
    // 目的の値に対して使える硬貨の最大数か、持っている数かの小さい方を使う
    $useCount = min(floor($targetValue / $coinCase->type), $coinCase->count);
    // 目的の値を減らし、使った数を合計する
    $targetValue -= $useCount * $coinCase->type;
    $totalUseCount += $useCount;
    // 使った硬貨を別なケースで保存する
    if ($useCount) {
        $usedCoinCases[] = new CoinCase($coinCase->type, $useCount);
    }
}

echo "answer:{$totalUseCount}\n== detail ==\n";
var_dump($usedCoinCases);