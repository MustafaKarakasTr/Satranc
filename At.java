public class At extends Tas{
    At(int _x,int _y,boolean _beyaz_mi){
        super(_x,_y,_beyaz_mi);// koordinatlarını koyar
        //System.out.println(x+"? "+y);
    }
    protected boolean oyna(int _x,int _y){
        boolean a=super.oyna(_x,_y);
        if(baslangicta_mi)
            baslangicta_mi = false;
        return a;
    }
     int[][] koordinatlar(Tas[][] tahta){
         int [][]arr = {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};
         int[][] _koordinatlar = new int[9][2]; // 8 + 1
         int k=0;
         for(int i=0;i<8;i++){
             if(validPointtoGo(tahta,x+arr[i][0],y+arr[i][1]) ){
                _koordinatlar[k][0] = x+arr[i][0];
                _koordinatlar[k][1] = y+arr[i][1];
                k++;
             }
         }
         _koordinatlar[k][0]=-1;
         _koordinatlar[k][1]=-1;
         return _koordinatlar;

     }
}
