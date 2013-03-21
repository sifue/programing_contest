<?php
class Memo
{
    private $memo = [];
    /**
     * メモを利用するフィボナッチ数列を求める関数
     * @param int $n
     * @return int
     */
    public function fib($n)
    {
        if ($n <= 1) {
            return $n;
        }
        if (isset($this->memo[$n])) {
            return $this->memo[$n];
        }
        return $this->memo[$n] = $this->fib($n - 1) + $this->fib($n - 2);
    }
}
echo((new \Memo())->fib(40));
