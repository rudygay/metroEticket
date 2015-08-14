package com.huaruan.other;

import java.util.ArrayList;
import java.util.Hashtable;

public class Search {
	public String doLineSearch(String mBeginstation,String mEndStation) {  
        String str;
        PathSearch mPathSearch=new PathSearch(); 
        mPathSearch.setBeginVertex(mBeginstation);  
       
		mPathSearch.setEndVertex(mEndStation);  
        mPathSearch.startSearch();     
        mPathSearch.makePathPrompt();  
        str= mPathSearch.getResult();
        return str;
        }  
	class GraphEntry {  
	    private ArrayList<Integer> list;  
	    private int line;  
	    private int info;  
	      
	    public GraphEntry(){  
	        list = new ArrayList<Integer>();  
	    }  
	      
	    public void addLine(int line){  
	        this.line |= line;  
	    }  
	      
	    public int getLine(){  
	        return line;  
	    }  
	    public void setInfo(int info){  
	        this.info = info;  
	    }  
	      
	    public int getInfo(){  
	        return info;  
	    }  
	    public void insertItem(int id){  
	        list.add(id);  
	    }  
	          
	    public int getItem(int index){  
	        int id = list.get(index);  
	        return id;  
	    }  
	      
	    public int size(){  
	        return list.size();  
	    }  
	}  
	class TableEntry {  
	    private boolean known;  
	    private int dist;  
	    private int path;  
	      
	    public void setKnown(boolean flag){  
	        known = flag;  
	    }  
	      
	    public boolean getKnown(){  
	        return known;  
	    }  
	      
	    public void setDist(int dist){  
	        this.dist = dist;  
	    }  
	      
	    public int getDist(){  
	        return dist;  
	    }  
	      
	    public void setPath(int path){  
	        this.path = path;  
	    }  
	      
	    public int getPath(){  
	        return path;  
	    }  
	}  

	class PathSearch {  
		 
	    private Hashtable<Integer, String> mHashTable1;  
	    private Hashtable<String, Integer> mHashTable2;   
	    private GraphEntry graph[];  
	    private TableEntry table[];  
	    private int vertexNum;   
	    private String beginStation;  
	    private int beginVertex;  
	    private String endStation;  
	    private int endVertex;  
	    private String mResult;  
	    private ArrayList<Integer> mPath;  
	 
	    /** construct function */ 
	    public PathSearch(){  
	        initHashTable();      
	        initGraph();  
	        makeGraph();  
	        addGraphDetail();  
	        initTable();  
	    }  
	      
	    /** hash table init */ 
	    public void initHashTable(){          
	        int i, j, id = 0;  
	          
	        mHashTable1 = new Hashtable<Integer, String>();  
	        mHashTable2 = new Hashtable<String, Integer>();       
	          
	        for (i = 0; i < ResFinalVars.lines.length; i++)  
	        {  
	            for (j = 0; j < ResFinalVars.lines[i].length; j++){  
	                if (mHashTable1.contains(ResFinalVars.lines[i][j]) == false)  
	                {  
	                    mHashTable1.put(id, ResFinalVars.lines[i][j]);  
	                    mHashTable2.put(ResFinalVars.lines[i][j], id);  
	                    id++;  
	                }                 
	            }             
	        }  
	          
	        vertexNum = id;  
	    }  
	      
	    /** return id by string */ 
	    public int findIdByKey(String key){  
	        return mHashTable2.get(key);  
	    }  
	      
	    /** return string by id */ 
	    public String findStrById(int id){  
	        return mHashTable1.get(id);  
	    }  
	      
	    /** graph init */ 
	    public void initGraph(){  
	        int vertexNum = this.getVertexNum();  
	        graph = new GraphEntry[vertexNum];  
	        for (int i = 0; i < vertexNum; i++)  
	        {  
	            graph[i] = new GraphEntry();      
	        }  
	          
	        mPath = new ArrayList<Integer>();  
	    }  
	      
	    /** graph make */ 
	    public void makeGraph(){  
	        int i, j, id, index;  
	        for (i = 0; i < ResFinalVars.lines.length; i++)  
	        {  
	            for (j = 1; j < ResFinalVars.lines[i].length - 1; j++)  
	            {             
	                id = findIdByKey(ResFinalVars.lines[i][j]);  
	                graph[id].insertItem(findIdByKey(ResFinalVars.lines[i][j + 1]));  
	                graph[id].insertItem(findIdByKey(ResFinalVars.lines[i][j - 1]));  
	            }    
	            index = 0;  
	            graph[findIdByKey(ResFinalVars.lines[i][index])].insertItem(findIdByKey(ResFinalVars.lines[i][index + 1]));  
	            index = ResFinalVars.lines[i].length - 1;  
	            graph[findIdByKey(ResFinalVars.lines[i][index])].insertItem(findIdByKey(ResFinalVars.lines[i][index - 1]));                        
	        }       
	    }  
	      
	      
	    /** add graph detail, line number and other info */ 
	    public void addGraphDetail(){  
	          
	        int i, j, id;  
	        // add line info          
	        for (i = 0; i < ResFinalVars.lines.length; i++)  
	        {  
	            for (j = 0; j < ResFinalVars.lines[i].length; j++)  
	            {  
	                id = findIdByKey(ResFinalVars.lines[i][j]);  
	                graph[id].setInfo(ResFinalVars.NORMAL);   
	                switch(i){  
	                case 0:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE1.getValue());  
	                    break;  
	                case 1:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE2.getValue());  
	                    break;  
	                case 2:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE3.getValue());  
	                    break;  
	                case 3:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE3.getValue());  
	                    break;  
	                case 4:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE4.getValue());  
	                    break;  
	                case 5:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE5.getValue());  
	                    break;  
	                case 6:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINE8.getValue());  
	                    break;  
	                case 7:  
	                    graph[id].addLine(ResFinalVars.LINESINFO.LINEGF.getValue());  
	                    break;  
	                }  
	            }  
	        }  
	          
	        // add transit station info  
	        for (i = 0; i < ResFinalVars.transit_stations.length; i++)  
	        {  
	            id = findIdByKey(ResFinalVars.transit_stations[i]);  
	            graph[id].setInfo(ResFinalVars.TRANSIT);  
	        }  
	    }  
	      
	 
	    /** table init */ 
	    public void initTable(){  
	        table = new TableEntry[this.getVertexNum()];  
	        for (int i = 0; i < table.length; i++)  
	        {  
	            table[i] = new TableEntry();  
	        }  
	    }  
	      
	 
	    /** table clear */ 
	    public void clearTable(){  
	        for (int i = 0; i < table.length; i++)  
	        {  
	            table[i].setKnown(false);  
	            table[i].setDist(ResFinalVars.INFINITE);  
	            table[i].setPath(ResFinalVars.UNKNOWN);  
	        }         
	    }  
	      
	    /** set begin vertex by id */ 
	    public boolean setBeginVertex(int id){  
	        clearTable();  
	        if (mHashTable1.containsKey(id) == true)  
	        {  
	            beginVertex = id;  
	            beginStation = findStrById(id);  
	            table[id].setDist(0);  
	            return true;  
	        }  
	        return false;  
	    }     
	      
	    /** set begin vertex by name */ 
	    public boolean setBeginVertex(String name){  
	        int id;       
	        clearTable();  
	        if (mHashTable2.containsKey(name) == true)  
	        {  
	            id = findIdByKey(name);  
	            beginVertex = id;  
	            beginStation = name;  
	            table[id].setDist(0);  
	            return true;  
	        }  
	        return false;                 
	    }  
	      
	    /** get begin vertex in id */ 
	    public int getBeginVertexId(){  
	        return beginVertex;  
	    }  
	      
	    /** get end vertex in name */ 
	    public String getBeginVertexName(){  
	        return beginStation;  
	    }  
	      
	    /** set end vertex by id */ 
	    public boolean setEndVertex(int id){  
	        if (mHashTable1.containsKey(id) == true)  
	        {  
	            endVertex = id;  
	            endStation = findStrById(id);  
	            return true;  
	        }  
	        return false;  
	    }  
	      
	    /** set end vertex by name */ 
	    public boolean setEndVertex(String name){  
	        if (mHashTable2.containsKey(name) == true)  
	        {             
	            endVertex = findIdByKey(name);  
	            endStation = name;  
	            return true;  
	        }  
	        return false;  
	    }  
	      
	    /** get end vertex in id */ 
	    public int getEndVertexId(){  
	        return endVertex;  
	    }  
	      
	    /** get end vertex in name */ 
	    public String getEndVertexName(){  
	        return endStation;  
	    }  
	      
	      
	    /** get Vertex number */ 
	    public int getVertexNum(){  
	        return vertexNum;  
	    }  
	      
	      
	    /** startSearch */ 
	    public void startSearch(){  
	        int currDist, v, w, i;  
	        for (currDist = 0; currDist < this.getVertexNum(); currDist++)  
	        {  
	            for (v = 0; v < this.getVertexNum(); v++)  
	            {  
	                if ((table[v].getKnown() == false) && (table[v].getDist() == currDist))  
	                {  
	                    table[v].setKnown(true);  
	                    for (i = 0; i < graph[v].size(); i++)  
	                    {  
	                        w = graph[v].getItem(i);  
	                        if (table[w].getDist() == ResFinalVars.INFINITE)  
	                        {  
	                            table[w].setDist(currDist + 1);  
	                            table[w].setPath(v);  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	    }  
	      
	      
	    /** find path */ 
	    public void findPath(int id){  
	        if (table[id].getDist() != 0)  
	        {  
	            findPath(table[id].getPath());  
	        }         
	        mPath.add(id);  
	    }  
	          
	    /** get line info */ 
	    public String getLineInfo(int index){  
	        String ret = "";  
	        switch(index)  
	        {  
	        case ResFinalVars.LINE1:  
	            ret += ResFinalVars.lines_number[0];  
	            break;  
	        case ResFinalVars.LINE2:  
	            ret += ResFinalVars.lines_number[1];  
	            break;  
	        case ResFinalVars.LINE3:  
	            ret += ResFinalVars.lines_number[2];  
	            break;  
	        case ResFinalVars.LINE4:  
	            ret += ResFinalVars.lines_number[3];  
	            break;  
	        case ResFinalVars.LINE5:  
	            ret += ResFinalVars.lines_number[4];  
	            break;  
	        case ResFinalVars.LINE8:  
	            ret += ResFinalVars.lines_number[7];  
	            break;  
	        case ResFinalVars.LINEGF:  
	            ret += ResFinalVars.lines_number[8];  
	            break;  
	        }  
	        return ret;  
	    }  
	      
	    public void makePathPrompt(){  
	        String ret = "", linenum = "";
	        String price1="票价";
	        
	        int i, id = beginVertex, previd, nextid, cnt = 0,price=0,stationcount=0;  
	          
	        // clear path history  
	        mPath.clear();    
	          
	        // find the path   
	        findPath(this.getEndVertexId());  
	          
	        if(mPath.size() == 1) // beginVertex == endVertex  
	        {  
	            ret = "您已经在 " + findStrById(id) + " 了， 无需乘坐地铁";  
	            mResult = ret;  
	            return ;  
	        }  
	              
	        for (i = 0; i < mPath.size(); i++)  
	        {  
	            id = mPath.get(i);                        
	            if (i == 0) // id == beginVertex  
	            {                             
	                nextid = mPath.get(i + 1);                
	                linenum = getLineInfo(graph[id].getLine() & graph[nextid].getLine());  
	                ret += "在 " + findStrById(id) + "乘坐" +linenum;  
	            }  
	            else if (i == mPath.size() - 1) // id == endVertex  
	            {  stationcount++;
	                cnt++;  
	                ret += "在"+findStrById(id)+"下车";  
	            }  
	            else if (graph[id].getInfo() == ResFinalVars.NORMAL)  
	            {  stationcount++;
	                cnt++;  
	            }  
	            else if (graph[id].getInfo() == ResFinalVars.TRANSIT)  
	            {     stationcount++;        
	                cnt++;  
	                if (i + 1 < mPath.size()) // make sure (i + 1) not overflow  
	                {  
	                    previd = mPath.get(i - 1);  
	                    nextid = mPath.get(i + 1);  
	                    linenum = getLineInfo(graph[id].getLine() & graph[nextid].getLine());  
	                      
	                    if ((graph[previd].getLine() & graph[id].getLine()) != (graph[nextid].getLine() & graph[id].getLine()))  
	                    {  
	                        ret += "到" + findStrById(id) + "换乘" + linenum;  
	                        cnt = 0;  
	                    }  
	                }  
	            }  
	        }  
	          
	       
	        if(stationcount<1){  
	        	price=0;
	       }
	        else if (stationcount<=4&&stationcount>=1){
	        	price=2;
	        }
	        else if(stationcount<=6){
	        	price=3;
	        }
	        else if(stationcount<=8){
	        	price=4;
	        }
	        else if(stationcount<=10){
	        	price=5;}
	         else if(stationcount<=12){
	    	price=6;}
	         else if(stationcount<=15){
	        	 price=7;
	         }
	         else if(stationcount<=18){
	        	 price=8;
	         }
	         else if( stationcount<=20){
	        	 price=9;
	         }
	         else if(stationcount<=22){
	        	 price=10;
	         }
	         else if(stationcount<=24){
	        	 price=11;
	         }
	         else 
	        	 price=12;
	    
	        	 
	       
	        //ret+=price1+price+"你总共要做"+stationcount;
	        
	        mResult =ret+"，全程"+stationcount+"站"+"@"+ price;
	    }  
	      
	      
	    /** get result */ 
	    public String getResult(){        
	        return mResult;  
	    }   

	}
}
final class ResFinalVars{  
    public static final int INFINITE = 0xffff;  
    public static final int UNKNOWN = -1;  
    public static final int TRANSIT = 0;  
    public static final int NORMAL = 1;  
      
    public static enum LINESINFO{  
        LINE1(1), LINE2(2), LINE3(4), LINE4(8), LINE5(0x10), LINE6(0x20), LINE7(0x40), LINE8(0x80), LINEGF(0x100);        
        private final int value;  
        private LINESINFO(int value){  
            this.value = value;  
        }  
          
        public int getValue(){  
            return this.value;  
        }  
    };  
      
    public static final int LINE1 = 1;  
    public static final int LINE2 = 2;  
    public static final int LINE3 = 4;  
    public static final int LINE4 = 8;  
    public static final int LINE5 = 0x10;  
    public static final int LINE6 = 0x20;  
    public static final int LINE7 = 0x40;  
    public static final int LINE8 = 0x80;  
    public static final int LINEGF = 0x100;  
      
      
      
    public static final String lines_number[] = {  
        "地铁一号线", "地铁二号线", "地铁三号线", "地铁四号线", "地铁五号线", "地铁六号线", "", "地铁八号线", "地铁广佛线" 
    };  
      
    public static final String line1_stations[] = {  
        "广州东站", "体育中心", "体育西路", "杨箕", "东山口", "烈士陵园", "农讲所", "公园前", "西门口", "陈家祠", "长寿路", "黄沙", "芳村", "花地湾", "坑口", "西朗",  
    };      
 
    public static final String line2_stations[] = {  
        "广州南站", "石壁", "会江", "南浦", "洛溪", "南洲", "东晓南", "江泰路", "昌岗", "江南西", "市二宫", "海珠广场", "公园前", "纪念堂", "越秀公园", "广州火车站", "三元里", "飞翔公园", "白云公园", "白云文化广场", "萧岗", "江夏", "黄边", "嘉禾望岗",  
    };    
 
    public static final String line3a_stations[] = {  
        "天河客运站", "五山", "华师", "岗顶", "石牌桥", "体育西路", "珠江新城", "赤岗塔", "客村", "大塘", "沥滘", "厦滘", "大石", "汉溪长隆", "市桥", "番禺广场" 
    };    
 
    public static final String line3b_stations[] = {  
        "体育西路", "林和西", "广州东站", "燕塘", "梅花园", "京溪南方医院", "同和", "永泰", "白云大道北", "嘉禾望岗", "龙归", "人和", "机场南站",  
    };  
 
 
    public static final String line4_stations[] = {  
        "黄村", "车陂", "车陂南", "万胜围", "官洲", "大学城北", "大学城南", "新造", "石碁", "海傍", "低涌", "东涌", "黄阁汽车城", "黄阁", "蕉门", "金洲",  
    };      
 
    public static final String line5_stations[] = {  
        "滘口", "坦尾", "中山八", "西场", "西村", "广州火车站", "小北", "淘金", "区庄", "动物园", "杨箕", "五羊邨", "珠江新城", "猎德", "潭村", "员村", "科韵路", "车陂南", "东圃", "三溪", "鱼珠", "大沙地", "大沙东", "文冲",  
    };  
 
    public static final String line6_stations[] = {  
    	"浔峰岗","横沙","沙贝","河沙","坦尾","如意坊","黄沙","文化公园","一德路","海珠广场","北京路","越秀南","东湖","东山口","区庄","黄花岗","沙河顶","沙河","天平架","燕塘","天河客运站","长湴",
          
    };  
              
    public static final String line7_stations[] = {  
    };  
 
    public static final String line8_stations[] = {  
        "万胜围", "琶洲", "新港东", "磨碟沙", "赤岗", "客村", "鹭江", "中大", "晓港", "昌岗", "宝岗大道", "沙园", "凤凰新村",  
    };  
      
    public static final String gfline_stations[] = {  
        "西朗", "菊树", "龙溪", "金融高新区", "千灯湖", "(虫雷)岗", "南桂路", "桂城", "朝安", "普君北路", "祖庙", "同济路", "季华园", "魁奇路站" 
    };  
      
    public static final String[][] lines = {  
        line1_stations,  
        line2_stations,  
        line3a_stations,  
        line3b_stations,  
        line4_stations,  
        line5_stations,  
        line8_stations,  
        gfline_stations,
        line6_stations,
        
    };  
      
    public static final String[] transit_stations = {  
        "西朗", "广州东站", "万胜围", "客村", "昌岗", "车陂南", "珠江新城", "体育西路", "杨箕", "公园前", "广州火车站", "嘉禾望岗","黄沙","东山口","区庄","燕塘","天河客运站","坦尾",
    };  
}
