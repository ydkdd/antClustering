
package antclustering;

import java.util.Random;
import static antclustering.antOperation.is_stayingAnt;
import static antclustering.antOperation.has_object;
import static antclustering.antOperation.is_carryingObject;
import static antclustering.antOperation.is_cellEmpty;
import static antclustering.antOperation.is_unloading;


public class normal1 {
    static int Range = 2;
    
    static Grand grand ;
    //蟻
    static int ant_E;
    static ant[] ant; 
    //*****************************************************************************//
    static int MAX_kind;
    static int MAX_ant;
    static int ANT;
    //繰り返し回数
    static int Iteration;
    static int limitCount;
  
    public static void local_initial_parameters(Data data){
//        grand.Check();
        setData(data);
 //       NotAntCheck(grand);
 //       System.out.println("\nクラスタリング開始");
        Clustering(grand,ant);
 //       System.out.println("クラスタリング終了");
   //     System.out.println("\n/****************************************************************************/");
        Check(grand,ant);
    }

    public static void Clustering(Grand grand,ant[] ant) {
        int Memo=0;
        grand.set(grand.state.length);
        Random rnd = new Random();
        //「Interation」の数だけ繰り返し
        for(int t=0;t<Iteration;t++){
            //回数表示
//            Print(t,grand);
            //すべての蟻で実行
            for(int an=0;an<ant.length;an++){
                //drop
                if(is_carryingObject(ant[an])&&is_cellEmpty(ant[an].Location,grand.state)){
                    double F = f(ant[an],grand.state);
                    double R = Math.random(),P=Pdrop(F);
                    if(P>R){
                        ant[an].Memory.set_memory(ant[an].Location,ant[an].State);
                        grand.state[ant[an].Location.y][ant[an].Location.x]=ant[an].State;
                        ant[an].State=0;
                    }
                //pick
                }else if(is_unloading(ant[an])&&has_object(ant[an].Location,grand.state)){
                    double F = f(ant[an],grand.state);
                    double R = Math.random(),P=Ppick(F);
                    if(P>R){
                        ant[an].State=grand.state[ant[an].Location.y][ant[an].Location.x];
                        grand.state[ant[an].Location.y][ant[an].Location.x]=0;
                        //該当なし
                        if(ant[an].Memory.serch_memory(ant[an].State)&&!grand.MovingANT(ant[an].Memory.P[ant[an].State].x,ant[an].Memory.P[ant[an].State].y, ant[an])){
                            int x = ant[an].Memory.P[ant[an].State].x,y = ant[an].Memory.P[ant[an].State].y;
                            //ジャンプ先予定地に蟻がいなければジャンプ
                            if(is_stayingAnt(grand.ant,ant[an].Memory.P[ant[an].State]))
                                if(!grand.MovingANT(x,y,ant[an]))
                                    System.out.println("s");
                            else//予定地に方向に向かって移動？ 
                                System.out.println("dame? ("+x+","+y+") "+grand.state[y][x]);

                            //ジャンプ後一回数移動を試みる
                            wander(t,ant[an],grand);
                        }
                    }
                }
                //ランダム移動
                wander(t,ant[an],grand);
            }
            
        }
        System.out.println("\r ");   
    }
    private static double Ppick(double F){
        return Math.pow(1-F, 2);
    }
    private static double Pdrop(double F){
        return Math.pow(F, 2);
    }
    //**********************************************************************************************//    
    //***配置状態の表示(蟻有)***//
    public static void Check(Grand grand,ant[] ant){    
        int object[]=new int[MAX_kind+1];
        int object2[]=new int[ANT];
        int[][] A = new int[grand.state.length][grand.state.length],B = new int[grand.state.length][grand.state.length];
//        for(int k=0;k<(int)(MAX_ant*0.2);k++){
        for(int k =0 ;k<ant.length;k++){
            A[ant[k].Location.y][ant[k].Location.x]++;
        }
        for(int k=0;k<ant.length;k++){
            B[ant[k].Location.y][ant[k].Location.x]=ant[k].State;
        }
        for (int i=0;i<grand.state.length;i++) {
            //要素の様子
            for(int j=0;j<grand.state.length;j++){
                if(grand.state[i][j]!=0){
                    System.out.print(grand.state[i][j] + " ");
                    
                    object[grand.state[i][j]]++;
                }else if(B[i][j]!=0)
                    System.out.print(B[i][j] + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.print("        ");
            //蟻の様子
            for(int j=0;j<grand.state.length;j++){
                if(grand.ant[i][j]!=0&&grand.ant[i][j]<10)
                    System.out.print(0+""+grand.ant[i][j] + " ");
                else if(grand.ant[i][j]!=0)
                    System.out.print(grand.ant[i][j] + " ");
                else
                    System.out.print(0+""+0 + " ");
            }
            System.out.print("        ");
            //蟻の様子2
            for(int j=0;j<grand.state.length;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.print("        ");
            //置かれていない要素
            for(int j=0;j<grand.state.length;j++){
                System.out.print(B[i][j]+" ");
            }
            
            System.out.println("");
        }
        System.out.println("");
        //オブジェクトを持ち上げている蟻の表示
 /*       for(int an=0;an<ant.length;an++){
            if(ant[an].State!=0){
         //       System.out.println("antNo."+(an+1)+" ("+ant[an].Location.x+","+ant[an].Location.y+") = "+ant[an].State);
                object2[ant[an].State]++;
            }
        }
        for(int b=1;b<object.length;b++)
            System.out.println("object "+b+" = "+object[b]);
        for(int b=0;b<=MAX_kind;b++)
            if(object2[b]!=0)
                System.out.println(b+" objectを持つあり = "+object2[b]);*/
    }
    //***配置状態の表示(蟻無)***//
    public static void NotAntCheck(Grand grand){
        for (int[] loop : grand.state) {
            for (int j = 0; j<grand.state.length; j++) {
                System.out.print(loop[j] + " ");   
            }
            System.out.println("");
        }
    }
    public static void CheckAnt(Grand grand){
        for (int[] loop : grand.ant) {
            for (int j = 0; j<grand.ant.length; j++) {
                System.out.print(loop[j] + " ");
            }
            System.out.println("");
        }       
    }
    public static void CheckAnt2(Grand grand,ant[] ant){
        int[][] A = new int[grand.state.length][grand.state.length];
        for(int k=0;k<(int)(ant.length*0.2);k++){
            A[ant[k].Location.y][ant[k].Location.x]++;
        }
        for (int i=0;i<grand.state.length;i++) {
            for(int j=0;j<grand.state.length;j++){
                System.out.print(A[j][i]+" ");
            }
            System.out.println();
        }
        
    }
    //***表示***//
    public static void Print(int i,Grand grand){
        if(i==Iteration/3){
                System.out.print("\r途中結果\n");
                NotAntCheck(grand);
                System.out.print("--------------------"+i+"--------------------");
            }
            else if(Iteration>=1000000&&i==0){
                System.out.print("\r");
                System.out.print("--------------------"+i+"--------------------");
            }else if(i%limitCount==0){
                System.out.print("\r");
                System.out.print("--------------------"+i+"--------------------");
            }else if(i==(limitCount-1)){
                System.out.print("\r");
            }
    }
    public static void wander(int t, ant ant, Grand grand) {
        M.Moves(ant, grand);
    }
    
    //***ユークリッド距離の計算***//
    static double EuclideanLenght(int x1,int y1,int x2,int y2){
        double state=Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        return state;
    }
    //なんとかなった？
    static double f(ant ant,int[][] grand) {
        double result = 0,T=Threshold(),value=0,lenght;
        int state = ant.State,X = ant.Location.x,Y = ant.Location.y;
        //蟻が何も持っていないときはその場に落ちているもので判断
        if(state==0&&grand[Y][X]!=0)
            state=grand[Y][X];
        //ある範囲内での類似度の計算
        for(int y=ant.Location.y-Range;y<=ant.Location.y+Range;y++)
            for(int x=ant.Location.x-Range;x<=ant.Location.x+Range;x++){
                if((y>=0&&y<grand.length)&&(x>=0&&x<grand.length)){
                    lenght = EuclideanLenght(x,y,X,Y);
                    //類似度の計算
                    if(grand[y][x]==state&&lenght!=0){
                        result += 1-lenght/T;
//                        S++
                    }else if(grand[y][x]==state&&lenght==0){
                        result += 1;
                    }
                    //最大値の計算
                    if(lenght==0)
                        value +=1;
                    else
                        value += 1-lenght/T;
                }
            }
        //蟻が知覚できる範囲のマスで割る
//        return Math.max(0, result/S);
        return Math.max(0, result/value);
    }
    //閾値
    static double Threshold(){
//        return A*(1+(V-1)/V);
        return 5.0;
        //A*(1+2/3)=5/3*1/2=5/6
    }

    private static void setData(Data data) {
        grand = data.grand;
    //蟻
        ant_E = data.ant_E;
        ant = data.ant.clone(); 
    //*****************************************************************************//
        MAX_kind = data.MAX_kind;
        MAX_ant = data.MAX_ant;
        ANT = data.ANT;
    //繰り返し回数
        Iteration = data.Iteration;
        limitCount = data.limitCount;
    }
}