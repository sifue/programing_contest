<?php

/**
 * Union-Find木におけるノードを表すクラス
 */
class Node
{
    /**
     * @var int 親のindex 自身が根の場合は自身のindexとなる
     */
    private $parentIndex;
    /**
     * @var int 自ノードのランク
     */
    private $rank;

    function __construct($parentIndex, $rank)
    {
        $this->parentIndex = $parentIndex;
        $this->rank = $rank;
    }

    /**
     * @return int
     */
    public function getParentIndex()
    {
        return $this->parentIndex;
    }

    /**
     * @return int
     */
    public function getRank()
    {
        return $this->rank;
    }

    /**
     * @param int $parentIndex
     */
    public function setParentIndex($parentIndex)
    {
        $this->parentIndex = $parentIndex;
    }

    /**
     * @param int $rank
     */
    public function setRank($rank)
    {
        $this->rank = $rank;
    }

    /**
     * ランクを一つ増加させる
     */
    public function incrementRank()
    {
        $this->rank += 1;
    }
}

/**
 * 配列のインデックスでノードを認識するUnion-Find木の実装
 */
class UnionFindTree
{
    /**
     * @var Node[] ノードの配列
     */
    private $nodes = [];

    /**
     * @param $nodeCount
     */
    public function  __construct($nodeCount)
    {
        for ($i = 0; $i < $nodeCount; $i++) {
            $this->nodes[$i] = new Node($i, 0);
        }
    }

    /**
     * 親のインデックスを取得して、縮約も行う
     * @param $target
     * @return int 親のインデックスを取得する
     */
    public function findRoot($target)
    {
        $parentIndex = $this->nodes[$target]->getParentIndex();
        if($parentIndex === $target){
            // 親が自分自身ならば根を発見
            return $target;
        } else {
            // 親のインデックスを元に再帰で根のインデックスを求める
            $rootIndex = $this->findRoot($parentIndex);
            // 自身の親を求まった親に更新する
            $this->nodes[$target]->setParentIndex($rootIndex);
            return $rootIndex;
        }
    }

    /**
     * インデックスxとyのグループを併合する
     * @param int $x
     * @param int $y
     */
    public function unite($x, $y)
    {
        // 同じグループなら何もしない
        $xRootIndex = $this->findRoot($x);
        $yRootIndex = $this->findRoot($y);
        if ($xRootIndex === $yRootIndex) {
            return;
        }

        // xがrankが小さいなら、xの親をyにする
        // そうでないなら、yの親をxにしてランクを増やす
        $xRootNode = $this->nodes[$xRootIndex];
        $yRootNode = $this->nodes[$yRootIndex];
        if ($xRootNode->getRank() < $yRootNode->getRank()) {
            $this->nodes[$x]->setParentIndex($y);
        } else {
            $this->nodes[$y]->setParentIndex($x);
            if ($xRootNode->getRank() === $yRootNode->getRank()) {
                $xRootNode->incrementRank();
            }
        }
    }

    /**
     * インデックスxとyが同じ集合に所属するか否かを返す
     * @param int $x
     * @param int $y
     * @return bool 同じ集合にいるか否か
     */
    public function isSameGroup($x, $y)
    {
        return $this->findRoot($x) === $this->findRoot($y);
    }
}

// [0,1,2,3,4][5,6,7]の8個でグループを作り判定してみある
$unindFindTree = new UnionFindTree(8);

$unindFindTree->unite(0, 1);
$unindFindTree->unite(1, 2);
$unindFindTree->unite(2, 3);
$unindFindTree->unite(3, 4);

$unindFindTree->unite(5, 6);
$unindFindTree->unite(6, 7);

echo ($unindFindTree->isSameGroup(0, 4) ? '同じ' : '違う' ). "\n"; // 同じ
echo ($unindFindTree->isSameGroup(4, 5) ? '同じ' : '違う' ). "\n"; // 違う
echo ($unindFindTree->isSameGroup(5, 7) ? '同じ' : '違う' ). "\n"; // 同じ