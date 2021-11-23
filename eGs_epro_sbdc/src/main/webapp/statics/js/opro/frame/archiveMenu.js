var archiveMenu = function(data , selectLayer , btnW , btnH){
    this.jsonData = data;
    this.selectLaayer = selectLayer;
    this.originLeft = btnW;
    this.cellHeight = btnH;    
};

archiveMenu.prototype.ready = function(){
    var oringinH = this.cellHeight;
    //data sort
    this.jsonData.sort(function(a,b) { return parseFloat(a.count) - parseFloat(b.count) });
    this.jsonData.reverse();
    var boardRanking = this.returnHeight();	
    //count에 따른 높이값 부여
    $.each(this.jsonData,function(i){
        this.rankingH = boardRanking[i][0] * oringinH;
        this.rankingT = boardRanking[i][1];
        this.rankingL = boardRanking[i][2];        
    });	
    this.addHtml();
};
//기본 높이값
archiveMenu.prototype.returnHeight = function(){
    var returnArray;
    if(this.jsonData.length == 8){
        //[칸수 , top , left , height]
        returnArray = [
             [5,this.cellHeight*0,this.originLeft*0]
            ,[4,this.cellHeight*0,this.originLeft*1]
            ,[3,this.cellHeight*0,this.originLeft*2]
            ,[2,this.cellHeight*3,this.originLeft*2]
            ,[1,this.cellHeight*5,this.originLeft*0]
            ,[1,this.cellHeight*4,this.originLeft*1]
            ,[1,this.cellHeight*5,this.originLeft*1]
            ,[1,this.cellHeight*5,this.originLeft*2]
        ];
    } else if(this.jsonData.length == 7) {
        //[칸수 , top , left , height]
        returnArray = [
             [6,this.cellHeight*0,this.originLeft*0] //1
            ,[4,this.cellHeight*0,this.originLeft*1] //2
            ,[3,this.cellHeight*1,this.originLeft*2] //3
            ,[2,this.cellHeight*4,this.originLeft*2] //4
            ,[1,this.cellHeight*0,this.originLeft*2] //5
            ,[1,this.cellHeight*4,this.originLeft*1] //6
            ,[1,this.cellHeight*5,this.originLeft*1] //7
        ];        
    } else if(this.jsonData.length == 6){
        //[칸수 , top , left , height]
        returnArray = [
             [6,this.cellHeight*0,this.originLeft*0] //1
            ,[5,this.cellHeight*0,this.originLeft*2] //2
            ,[3,this.cellHeight*1,this.originLeft*1] //3
            ,[2,this.cellHeight*4,this.originLeft*1] //4
            ,[1,this.cellHeight*0,this.originLeft*1] //5
            ,[1,this.cellHeight*5,this.originLeft*2] //6
        ];
    }
    return returnArray;
};
//html add
archiveMenu.prototype.addHtml = function(){
    var selectLaayer = this.selectLaayer;
	
    $.each(this.jsonData,function(i){
        var html = '<a href="' +this.link +'" class="archiveLayerBtn L colorSet'+i+'" style="top:'+this.rankingT+'px; left:'+this.rankingL+'px; height:'+this.rankingH+'px;"><div class="noticeTitle">'+this.title+'</div><div class="noticeLen">총 <span>'+this.count+'</span>건</div></a>';
        $(selectLaayer).append(html);
    });
};