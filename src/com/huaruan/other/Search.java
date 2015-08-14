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
	        String price1="Ʊ��";
	        
	        int i, id = beginVertex, previd, nextid, cnt = 0,price=0,stationcount=0;  
	          
	        // clear path history  
	        mPath.clear();    
	          
	        // find the path   
	        findPath(this.getEndVertexId());  
	          
	        if(mPath.size() == 1) // beginVertex == endVertex  
	        {  
	            ret = "���Ѿ��� " + findStrById(id) + " �ˣ� �����������";  
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
	                ret += "�� " + findStrById(id) + "����" +linenum;  
	            }  
	            else if (i == mPath.size() - 1) // id == endVertex  
	            {  stationcount++;
	                cnt++;  
	                ret += "��"+findStrById(id)+"�³�";  
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
	                        ret += "��" + findStrById(id) + "����" + linenum;  
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
	    
	        	 
	       
	        //ret+=price1+price+"���ܹ�Ҫ��"+stationcount;
	        
	        mResult =ret+"��ȫ��"+stationcount+"վ"+"@"+ price;
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
        "����һ����", "����������", "����������", "�����ĺ���", "���������", "����������", "", "�����˺���", "���������" 
    };  
      
    public static final String line1_stations[] = {  
        "���ݶ�վ", "��������", "������·", "���", "��ɽ��", "��ʿ��԰", "ũ����", "��԰ǰ", "���ſ�", "�¼���", "����·", "��ɳ", "����", "������", "�ӿ�", "����",  
    };      
 
    public static final String line2_stations[] = {  
        "������վ", "ʯ��", "�Ὥ", "����", "��Ϫ", "����", "������", "��̩·", "����", "������", "�ж���", "����㳡", "��԰ǰ", "������", "Խ�㹫԰", "���ݻ�վ", "��Ԫ��", "���蹫԰", "���ƹ�԰", "�����Ļ��㳡", "����", "����", "�Ʊ�", "�κ�����",  
    };    
 
    public static final String line3a_stations[] = {  
        "��ӿ���վ", "��ɽ", "��ʦ", "�ڶ�", "ʯ����", "������·", "�齭�³�", "�����", "�ʹ�", "����", "����", "�Ü�", "��ʯ", "��Ϫ��¡", "����", "��خ�㳡" 
    };    
 
    public static final String line3b_stations[] = {  
        "������·", "�ֺ���", "���ݶ�վ", "����", "÷��԰", "��Ϫ�Ϸ�ҽԺ", "ͬ��", "��̩", "���ƴ����", "�κ�����", "����", "�˺�", "������վ",  
    };  
 
 
    public static final String line4_stations[] = {  
        "�ƴ�", "����", "������", "��ʤΧ", "����", "��ѧ�Ǳ�", "��ѧ����", "����", "ʯ��", "����", "��ӿ", "��ӿ", "�Ƹ�������", "�Ƹ�", "����", "����",  
    };      
 
    public static final String line5_stations[] = {  
        "���", "̹β", "��ɽ��", "����", "����", "���ݻ�վ", "С��", "�Խ�", "��ׯ", "����԰", "���", "����ߗ", "�齭�³�", "�Ե�", "̶��", "Ա��", "����·", "������", "����", "��Ϫ", "����", "��ɳ��", "��ɳ��", "�ĳ�",  
    };  
 
    public static final String line6_stations[] = {  
    	"䱷��","��ɳ","ɳ��","��ɳ","̹β","���ⷻ","��ɳ","�Ļ���԰","һ��·","����㳡","����·","Խ����","����","��ɽ��","��ׯ","�ƻ���","ɳ�Ӷ�","ɳ��","��ƽ��","����","��ӿ���վ","����",
          
    };  
              
    public static final String line7_stations[] = {  
    };  
 
    public static final String line8_stations[] = {  
        "��ʤΧ", "����", "�¸۶�", "ĥ��ɳ", "���", "�ʹ�", "�ؽ�", "�д�", "����", "����", "���ڴ��", "ɳ԰", "����´�",  
    };  
      
    public static final String gfline_stations[] = {  
        "����", "����", "��Ϫ", "���ڸ�����", "ǧ�ƺ�", "(����)��", "�Ϲ�·", "���", "����", "�վ���·", "����", "ͬ��·", "����԰", "����·վ" 
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
        "����", "���ݶ�վ", "��ʤΧ", "�ʹ�", "����", "������", "�齭�³�", "������·", "���", "��԰ǰ", "���ݻ�վ", "�κ�����","��ɳ","��ɽ��","��ׯ","����","��ӿ���վ","̹β",
    };  
}
