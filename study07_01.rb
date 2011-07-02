# -*- encoding: UTF-8 -*-.
# 全順列組み合わせの全切り出し方を全探索
# N=7で処理に11分 例題の答えの一つは1050、[[[[[20, 10], 70], [50, 70]], [80, 100]]]

# 順列の配列を作成
def permutations(ary=[], k=ary.size)
  return [[]] if k < 1
  perm = []
  ary.each do |e|
    x = ary.dup
    x.delete_at(x.index(e))
    permutations(x,k-1).each do |p|
      perm << ([e] + p)
    end
  end
  perm
end

# 配列と結果をいれる候補のデータクラス
class Candidate
  attr_accessor :array, :cost
  def initialize(myArray=[],myCost=0)
    @array = myArray
    @cost = myCost
  end
end

# 配列、数値の時をまとめてコスト集計
def sum_cost(e)
  cost = 0
  if Array === e
    e.flatten.each { |board| cost += board }
  else
    cost += e
  end
  cost
end

###########以下実処理#############
require 'thread'
require 'set'
require 'benchmark'
puts Benchmark.measure{
  l = [5,8,8]
  # l = [20,80,70,50,100,10,70]
  lPerm = permutations(l, l.size).to_a # 板の順序の順列組み合わせ配列の配列
  minCansSet = Set.new
  lPerm.each_with_index do |boards, index| # 板の種類の順番順列ループ
    q = Queue.new
    q.push(Candidate.new(boards))
    (boards.size-1).times do # 結合回ループ
      postQ = Queue.new
      while !q.empty? do # その会の結合処理の候補ループ
        can = q.pop
        ary = can.array
        (0..(ary.size-2)).each do |i| # 現在の結合箇所候補分ループ
            postAry,postCost = [],can.cost
            ary.each_with_index do |e, j| # 結合とコスト計算処理
              next if j == i
              if j==(i+1)
                postCost += sum_cost(ary[j-1])
                postCost += sum_cost(e) 
                postAry.push([ary[j-1],e])
              else
                postAry.push e
              end
            end
            postQ.push(Candidate.new(postAry,postCost)) # 新候補追加
        end
      end
      q = postQ
    end
    
    # この順列における最小コストの候補をみつけ、最小と同じならセットに足す
    while !q.empty? 
      can = q.pop
      minCansAry  = minCansSet.to_a
      if minCansSet.size == 0 || can.cost < minCansAry[0].cost 
        minCansSet = Set.new([can])
      elsif can.cost == minCansAry[0].cost 
        minCansSet.add(can)
      end
    end
    puts "finish : #{index.to_s}/#{lPerm.size.to_s}"
  end
  # 結果を出力
  minCansSet.each{|e| p e} # 全出力
  puts Benchmark::CAPTION
}