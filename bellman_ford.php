<?php

/**
 * 辺を表すエンティティクラス
 */
class Edge {
    /**
     * @var string
     */
    private $from;
    /**
     * @var string
     */
    private $to;
    /**
     * @var int
     */
    private $cost;

    function __construct($cost, $from, $to)
    {
        $this->cost = $cost;
        $this->from = $from;
        $this->to = $to;
    }

    /**
     * @return int
     */
    public function getCost()
    {
        return $this->cost;
    }

    /**
     * @return string
     */
    public function getFrom()
    {
        return $this->from;
    }

    /**
     * @return string
     */
    public function getTo()
    {
        return $this->to;
    }
}

/**
 * ベルマンフォード法による最短距離を求める実装
 */
class BellmanFord {
    /**
     * @var string[]
     */
    private $vortexes = [];
    /**
     * @var Edge[]
     */
    private $edges = [];
    /**
     * @var int[]
     */
    private $distances = [];

    /**
     * フィールドデータの初期化
     */
    private function initialize()
    {
        // データ初期化
        $this->vortexes = ['A', 'B', 'C', 'D', 'E', 'F', 'G'];
        $this->edges[] = new Edge(5, 'A', 'C');
        $this->edges[] = new Edge(5, 'C', 'A');
        $this->edges[] = new Edge(2, 'A', 'B');
        $this->edges[] = new Edge(2, 'B', 'A');
        $this->edges[] = new Edge(4, 'C', 'B');
        $this->edges[] = new Edge(4, 'B', 'C');
        $this->edges[] = new Edge(2, 'C', 'D');
        $this->edges[] = new Edge(2, 'D', 'C');
        $this->edges[] = new Edge(6, 'B', 'D');
        $this->edges[] = new Edge(6, 'D', 'B');
        $this->edges[] = new Edge(10, 'B', 'E');
        $this->edges[] = new Edge(10, 'E', 'B');
        $this->edges[] = new Edge(1, 'D', 'F');
        $this->edges[] = new Edge(1, 'F', 'D');
        $this->edges[] = new Edge(3, 'E', 'F');
        $this->edges[] = new Edge(3, 'F', 'E');
        $this->edges[] = new Edge(5, 'E', 'G');
        $this->edges[] = new Edge(5, 'G', 'E');
        foreach ($this->vortexes as $vortex) {
            $this->distances[$vortex] = PHP_INT_MAX;
        }
    }

    /**
     * startPointからの最短距離を全て取得する
     * @param string $startPoint
     * @return int[]
     */
    public function getShortestPath($startPoint){
        // データ初期化
        $this->initialize();

        // スタート地点からの各頂点へのコストがコストが低いものに更新できなくなるまでループ
        $this->distances[$startPoint] = 0;
        while (true) {
            $isUpdated = false;
            // 全エッジをループする
            foreach ($this->edges as $edge) {
               // PHP_INT_MAXが入力されて辿り付く経路が見つかってないfromの辺は無視
               // かつ、辺のfromに指定されているコストと辺のコストを足した物が、既存のtoまでのコストより低ければ更新
               if($this->distances[$edge->getFrom()] != PHP_INT_MAX
                   && $this->distances[$edge->getTo()] > ($this->distances[$edge->getFrom()] + $edge->getCost())) {

                   // エッジの先に対するコストをより低いもので更新
                   $this->distances[$edge->getTo()] = $this->distances[$edge->getFrom()] + $edge->getCost();
                   $isUpdated = true;
               }
            }
            // 更新がなくなったら終了とする
            if (!$isUpdated) break;
        }
        return $this->distances;
    }
}

// Aを始点にした時を出力
var_dump((new BellmanFord())->getShortestPath('A'));
