public class Fil extends Tas{
    Fil(int _x,int _y,boolean _beyaz_mi){
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
         int [][]arr = {{1,1},{1,-1},{-1,1},{-1,-1}};
         int[][] _koordinatlar = new int[14][2]; // 13 + 1
         int k=0;
         k=FillCoordinates(tahta, arr, _koordinatlar, k, 0);
         k=FillCoordinates(tahta, arr, _koordinatlar, k, 1);
         k=FillCoordinates(tahta, arr, _koordinatlar, k, 2);
         k=FillCoordinates(tahta, arr, _koordinatlar, k, 3);



         /*for(int i=0;i<8;i++){
             if(validPointtoGo(tahta,x+arr[0][0],y+arr[0][1]) ){
                _koordinatlar[k][0] = x+arr[0][0];
                _koordinatlar[k][1] = y+arr[0][1];
                if(arr[0][0]>0){
                    arr[0][0]++;
                }
                else arr[0][0]--;
                if(arr[0][1]>0){
                    arr[0][1]++;
                }
                else arr[0][1]--;

                k++;
                
             }
             else
                break;
         }*/
         _koordinatlar[k][0]=-1;
         _koordinatlar[k][1]=-1;
         return _koordinatlar;

     }
     protected int FillCoordinates(Tas[][] tahta,int[][] arr,int[][] _koordinatlar,int k,int index){
        

            for(int i=0;i<8;i++){
                if(validPointtoGo(tahta,x+arr[index][0],y+arr[index][1]) ){
                _koordinatlar[k][0] = x+arr[index][0];
                _koordinatlar[k][1] = y+arr[index][1];
                
                
                if(tahta[x+arr[index][0]][y+arr[index][1]] != null && tahta[x+arr[index][0]][y+arr[index][1]].beyaz_mi != beyaz_mi){
                    return ++k;
                }
                else if(tahta[x+arr[index][0]][y+arr[index][1]] != null && tahta[x+arr[index][0]][y+arr[index][1]].beyaz_mi == beyaz_mi){
                    _koordinatlar[k][0]=-1;
                    _koordinatlar[k][1]=-1;

                }
                
                if(arr[index][0]>0){
                    arr[index][0]++;
                    }
                    else arr[index][0]--;
                    if(arr[index][1]>0){
                        arr[index][1]++;
                    }
                    else arr[index][1]--;
                    k++;
                    }
                    else
                        break;
                

            }
            
        
        return k;
    }
     /*private int FillCoordinates(Tas[][] tahta,int[][] arr,int[][] _koordinatlar,int k,int index){
        for(int i=0;i<8;i++){
            if(validPointtoGo(tahta,x+arr[index][0],y+arr[index][1]) ){
               _koordinatlar[k][0] = x+arr[index][0];
               _koordinatlar[k][1] = y+arr[index][1];
               
               
               if(tahta[x+arr[index][0]][y+arr[index][1]] != null && tahta[x+arr[index][0]][y+arr[index][1]].beyaz_mi != beyaz_mi){
                   return ++k;
               }
               else if(tahta[x+arr[index][0]][y+arr[index][1]] != null && tahta[x+arr[index][0]][y+arr[index][1]].beyaz_mi == beyaz_mi){
                   _koordinatlar[k][0]=-1;
                   _koordinatlar[k][1]=-1;

               }
               
               if(arr[index][0]>0){
                arr[index][0]++;
                }
                else arr[index][0]--;
                if(arr[index][1]>0){
                    arr[index][1]++;
                }
                else arr[index][1]--;
                k++;
                }
                else
                    break;
            

        }
        return k;
    }*/
}
