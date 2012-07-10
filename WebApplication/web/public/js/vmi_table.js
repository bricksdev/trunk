
//代码如下
var G = function(id){return document.getElementById(id)}
var EACH = function(o,fn){for(var i=0;i<o.length;i++){fn.call(o[i],i,o); if(i==o.length-1) return o}}
var _2009_ = function(){this.init.apply(this,arguments)};
_2009_.prototype={
 init:function(o){
  EACH(G(o.id).rows,function(i,O){i%2== +!!o.parity ? this.style.background = o.cor1:'';
   this['color'] = this.style.background;
   this.onmouseover = function(){this.style.background = o.cor2}
   this.onmouseout = function(){this.style.background = this['color']}
  })
 }
};
//用法如下.可设定奇偶#B0DD8C.#C2D4E2
new _2009_({id:'tb1',cor1:'#EFF4F8',cor2:'#ECD7F2'});
//new _2009_({id:'tb3',cor1:'#EFF4F8',cor2:'#ECD7F2'});
//new _2009_({id:'tb2',cor1:'#E5EDF0',cor2:'#E5EDF0',parity:'默认是奇数,设了就是偶数'});






