(function($){'use strict';var Custom={init:function(){this.dialKnob();this.extraAreaMorrisChart();this.knobChart();},dialKnob:function(){var el=$(".knobHomePage");if(!el.length)
return;el.knob({'format':function(value){return value+'%';}});},extraAreaMorrisChart:function(){var ctx=document.getElementById("extraAreaMorris");if(ctx===null)return;function generateData(){var ele=null;var data=[];for(var i=0;i<10;i++){ele={period:(2010+i)+'',lenovo:Math.ceil(Math.random()*100),dell:Math.ceil(Math.random()*100),hp:Math.ceil(Math.random()*100),}
data.push(ele);}
return data;}},knobChart:function(){var el=$('[data-plugin="knob"]');if(!el.length)return;el.knob();}};$(document).ready(function(){Custom.init();});})(jQuery);