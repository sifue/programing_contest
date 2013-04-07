<?php
/**
 * 辺を表すエンティティクラス
 */
class Edge {
    /**
     * @var string
     */
    private $to;
    /**
     * @var int
     */
    private $cost;

    function __construct($cost, $to)
    {
        $this->cost = $cost;
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
    public function getTo()
    {
        return $this->to;
    }
}


/**
 * priority値が低いほどtopに来るプライオリティーキュー
 */
class PriorityQueue extends SplPriorityQueue
{
    public function compare($priority1, $priority2)
    {
        if ($priority1 === $priority2) return 0;
        return $priority1 < $priority2 ? 1 : -1;
    }
}

/**
 * ダイクストラ法による最短距離を求める実装
 */
class Dijkstra {
    /**
     * @var string[]
     */
    private $vortexes = [];
    /**
     * @var Edge[] fromをキーとしたハッシュ構造にtoとcostのクラスを入れる
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
        foreach ($this->vortexes as $vortex) {
            $this->edges[$vortex] = [];
        }
        $this->edges['A'][] = new Edge(5, 'C');
        $this->edges['C'][] = new Edge(5, 'A');
        $this->edges['A'][] = new Edge(2, 'B');
        $this->edges['B'][] = new Edge(2, 'A');
        $this->edges['C'][] = new Edge(4, 'B');
        $this->edges['B'][] = new Edge(4, 'C');
        $this->edges['C'][] = new Edge(2, 'D');
        $this->edges['D'][] = new Edge(2, 'C');
        $this->edges['B'][] = new Edge(6, 'D');
        $this->edges['E'][] = new Edge(6, 'B');
        $this->edges['B'][] = new Edge(10, 'E');
        $this->edges['E'][] = new Edge(10, 'B');
        $this->edges['D'][] = new Edge(1, 'F');
        $this->edges['F'][] = new Edge(1, 'D');
        $this->edges['E'][] = new Edge(3, 'F');
        $this->edges['F'][] = new Edge(3, 'E');
        $this->edges['E'][] = new Edge(5, 'G');
        $this->edges['G'][] = new Edge(5, 'E');
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

        // キューを次に求める最短距離の起点の候補として使う
        $this->distances[$startPoint] = 0;
        $queue = new PriorityQueue();
        $queue->setExtractFlags(PriorityQueue::EXTR_BOTH); //priorityも取得する
        $queue->insert($startPoint, 0);
        while (!$queue->isEmpty()) {
            $queue->top();
            $pair = $queue->extract();
            $vortex = $pair['data'];
            $minDistance = $pair['priority'];
            if($this->distances[$vortex] < $minDistance) continue;
            // 現在の操作している始点の辺を全てループして隣接頂点の最短距離の頂点を更新していく
            foreach ($this->edges[$vortex] as $edge) {
                if ($this->distances[$edge->getTo()] > ($this->distances[$vortex] + $edge->getCost())) {
                    // エッジの先に対するコストをより低いもので更新
                    $this->distances[$edge->getTo()] = $this->distances[$vortex] + $edge->getCost();
                    $queue->insert($edge->getTo(), $this->distances[$edge->getTo()]);
               }
            }
        }
        return $this->distances;
    }
}

// Aを始点にした時を出力
var_dump((new Dijkstra())->getShortestPath('A'));
