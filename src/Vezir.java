package satranc;
public class Vezir extends Fil {
    Vezir(int _x,int _y,boolean _beyaz_mi){
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
         int[][] _koordinatlar = new int[28][2]; //27+1
         int [][] temp = kaleVeVezirIcinGidilebilecekYerler(tahta);
         int i=0;
         for(i=0;temp[i][0] != -1;i++){
          
            _koordinatlar[i][0]=temp[i][0];
            _koordinatlar[i][1]=temp[i][1];
        }
        temp = super.koordinatlar(tahta); // fil in koordinatlar methodu capraz gıtmemıze yardımcı oluyor
        for(int a =0 ;temp[a][0] != -1;a++,i++){
            _koordinatlar[i][0]=temp[a][0];
            _koordinatlar[i][1]=temp[a][1];
        }
        _koordinatlar[i][0]=-1;
        _koordinatlar[i][1]=-1;
        
        return _koordinatlar;
     }
}
