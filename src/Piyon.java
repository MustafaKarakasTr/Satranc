package satranc;
public class Piyon extends Tas{
    Piyon(int _x,int _y,boolean _beyaz_mi){
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
        /*
        4x2 bir array dondurur eger -1,-1 i gorene kdr gıdebilir
        */
        int[][] arr=new int[4][2]; // max bu kdr farklı yere gıdebilir
        /*for(int i=0;i<4;i++){
            arr[i][0]=-1;
            arr[i][1]=-1;
        }*/
        
        int i=0;
        if(!beyaz_mi){
            if(x<7 && tahta[x+1][y] == null){ // ileri
                arr[i][0]=x+1; //piyon bır adım ileri
                arr[i][1]=y;
                i++;
                if(baslangicta_mi && tahta[x+2][y] == null){//baslangıcta 2 adım gıdebilir
                    arr[i][0] = x+2;
                    arr[i][1]=y;
                    i++;
                }


            }
            if( x<7&& y<7&&tahta[x+1][y+1]!=null && tahta[x+1][y+1].beyaz_mi == true){ // sağ çaprazı yeme
                arr[i][0] = x+1;
                arr[i][1] =y+1;
                i++;
            }
            if(x<7&& y>0 && tahta[x+1][y-1]!=null && tahta[x+1][y-1].beyaz_mi == true){ // sol çaprazı yeme
                arr[i][0] = x+1;
                arr[i][1] =y-1;
                i++;
            }
        }
        else{
            if ( x> 0 &&tahta[x-1][y] == null){//siyah
                arr[i][0] = x-1;
                arr[i][1] = y;
                i++;
                if(baslangicta_mi && tahta[x-2][y] == null){

                    arr[i][0] = x-2;
                    arr[i][1]=y;
                    i++;
                }
            }
            if( x>0 && y<7 && tahta[x-1][y+1]!=null && tahta[x-1][y+1].beyaz_mi == false){ // sağ çaprazı yeme
                arr[i][0] = x-1;
                arr[i][1] =y+1;
                i++;
            }
            if( x>0 && y>0 && tahta[x-1][y-1]!=null && tahta[x-1][y-1].beyaz_mi == false){ // sağ çaprazı yeme
                arr[i][0] = x-1;
                arr[i][1] =y-1;
                i++;
            }
        }
        arr[i][0]=-1;
        arr[i][1]=-1;
        /*
        if(y!=7){
            if(beyaz_mi){
                arr[0][0]=x;
                arr[0][1]=y+1;
                if(baslangicta_mi && tahta[x][y+1]==null){
                    arr[1][0]=x;
                    arr[1][1]=y+2;
                    
                }
                
                
                if(x!=7 && tahta[x+1][y+1]!=null && (tahta[x+1][y+1].beyaz_mi != beyaz_mi ) ){
                    arr[2][0] = x+1;
                    arr[2][1] = y+1;

                }
                if(x!=0 && tahta[x-1][y+1]!=null && (tahta[x-1][y+1].beyaz_mi != beyaz_mi ) ){
                    arr[3][0] = x-1;
                    arr[3][1] = y+1;
                }
            }
            else{
                arr[0][0]=x;
                arr[0][1]=y-1;
                if(baslangicta_mi&&tahta[x][y-1]==null){ // siyah ve baslangıcta
                    arr[1][0] = x;
                    arr[1][1] = y-2;
                }
                if(x!=7 && tahta[x+1][y-1]!=null && (tahta[x+1][y-1].beyaz_mi != beyaz_mi ) ){
                    arr[2][0] = x+1;
                    arr[2][1] = y-1;
                }
                if(x!=0 && tahta[x-1][y-1]!=null && (tahta[x-1][y-1].beyaz_mi != beyaz_mi ) ){
                    arr[3][0] = x-1;
                    arr[3][1] = y-1;
                }

            }

        }*/
        return arr;

    }

      
}
