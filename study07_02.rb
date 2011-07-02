# -*- encoding: UTF-8 -*-.
# PQを使って最もコストの低いペアを結合して進めて行ったが、
# 全探索で得た値よりも小さくならず...。失敗。いいPQの使い方わからず。
# N=7で処理に3ms コスト1340、[[[[[[20, 10], 50], 70], [70, 70]], [80, 100]]]

# Priority queue with array based heap.
#
# This is distributed freely in the sence of 
# GPL(GNU General Public License).
#
# K.Kodama 2005/09/01.  push_array, pop_array
# Rick Bradley 2003/02/02. patch for Ruby 1.6.5. Thank you!
# K.Kodama 2001/03/10. 1st version

class PQueue

  attr_accessor :qarray # format: [nil, e1, e2, ..., en]
  attr_reader :size # number of elements
  attr_reader :gt # compareProc
  
  def initialize(compareProc=lambda{|x,y| x>y})
    # By default, retrieves maximal elements first. 
    @qarray=[nil]; @size=0; @gt=compareProc; make_legal
  end
  private :initialize

  def upheap(k)
    k2=k.div(2); v=@qarray[k];
    while ((k2>0)and(@gt[v,@qarray[k2]]));
      @qarray[k]=@qarray[k2]; k=k2; k2=k2.div(2)
    end;
    @qarray[k]=v;
  end
  private :upheap

  def downheap(k)
    v=@qarray[k]; q2=@size.div(2)
    loop{
      if (k>q2); break; end;
      j=k+k; if ((j<@size)and(@gt[@qarray[j+1],@qarray[j]])); j=j+1; end;
      if @gt[v,@qarray[j]]; break; end;
      @qarray[k]=@qarray[j]; k=j;
    }
    @qarray[k]=v;
  end;
  private :downheap

  def make_legal
    for k in 2..@size do; upheap(k); end;
  end;

  def empty?
    return (0==@size)
  end

  def clear
    @qarray.replace([nil]); @size=0;
  end;

  def replace_array(arr=[])
    # Use push_array.
    @qarray.replace([nil]+arr); @size=arr.size; make_legal
  end;
  
  def clone
    q=new; q.qarray=@qarray.clone; q.size=@size; q.gt=@gt; return q;
  end;

  def push(v)
    @size=@size+1; @qarray[@size]=v; upheap(@size);
  end;

  def push_array(arr=[])
    @qarray[@size+1,arr.size]=arr; arr.size.times{@size+=1; upheap(@size)}
  end;

  def pop
    # return top element.  nil if queue is empty.
    if @size>0;
      res=@qarray[1]; @qarray[1]=@qarray[@size]; @size=@size-1;
      downheap(1);
      return res;
    else return nil
    end;
  end;

  def pop_array(n=@size)
    # return top n-element as an sorted array. (i.e. The obtaining array is decreasing order.)
    # See also to_a.
    a=[]
    n.times{a.push(pop)}
    return a
  end;
  
  def to_a
    # array sorted as increasing order.
    # See also pop_array.
    res=@qarray[1..@size];
    res.sort!{|x,y| if @gt[x,y]; 1;elsif @gt[y,x]; -1; else 0; end;}
    return res
  end

  def top
    # top element. not destructive.
    if @size>0; return @qarray[1]; else return nil; end;
  end;

  def replace_top_low(v)
    # replace top element if v<top element.
    if @size>0; @qarray[0]=v; downheap(0); return @qarray[0];
    else @qarray[1]=v; return nil;
    end;
  end;

  def replace_top(v)
    # replace top element
    if @size>0; res=@qarray[1]; @qarray[1]=v; downheap(1); return res;
    else @qarray[1]=v; return nil;
    end;
  end;

  def each_pop
    # iterate pop. destructive. Use as self.each_pop{|x| ... }. 
    while(@size>0); yield self.pop; end;
  end;

  def each_with_index
    # Not ordered. Use as self.each_with_index{|e,i| ... }. 
    for i in 1..@size do; yield @qarray[i],i; end;
  end

end # class pqueue

# 組み合わせ作成
def make_comb(array, num)
  return [[]] unless num > 0
  size = array.size
  ret_arr = []
  (size-num + 1).times{|ind|
    ret_arr += clonecker([array[ind]],make_comb(array[(ind+1)..-1],num-1))
  }
  ret_arr
end
def clonecker(array1, array2)
  array2.map{|item|
    array1 + item
  }
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

class Cost
  attr_accessor :value
  def initialize()
    @value = 0
  end
  def add(n)
    @value += n
  end
end

# 最もコストの低いペアを作ってそれを作っていく再帰関数
def connectBoards(l,c)
  return l if l.size == 1
  connect_pairs = make_comb(l,2)
  pq = PQueue.new(proc{|x,y| sum_cost(x) < sum_cost(y)}) # コストの低い順に取れる
  pq.push_array(connect_pairs)
  connect_pair = pq.pop
  rem_pair = connect_pair.dup
  newL = [connect_pair]
  l.each{ |e|
    if rem_pair.include?(e)
      rem_pair.delete(e)
    else
      newL.push(e)
    end
  }
  connect_pair.each{|e| c.add(sum_cost(e)) }
  return connectBoards(newL,c)
end
###########以下実処理#############
require 'benchmark'
puts Benchmark.measure{
  # l = [5,8,8]
  l = [20,80,70,50,100,10,70]
  cost = Cost.new
  connected = connectBoards(l,cost)
  p connected
  p cost.value
  puts Benchmark::CAPTION
}
