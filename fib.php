<?php
/**
 * フィボナッチ数列を求める関数
 * @param int $n
 * @return int
 */
function fib($n)
{
    if ($n <= 1) {
        return $n;
    }
    return fib($n - 1) + fib($n - 2);
}
echo(fib(10));