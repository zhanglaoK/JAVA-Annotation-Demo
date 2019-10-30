package dianxin;

public class Two {

    public static void main(String[] args) {
        System.out.println(lea(1,2,3,4,5));
    }
    public static int lea(int a,int b,int c,int d,int e){
        int arrs [] = {a,b,c,d,e};
        int res = 0;
        for (int i =0;i<5;i++){
            int count = 0;
            for(int j=0;j<5;j++){

                if(arrs[i]%arrs[j]==0){
                    count++;
                }
            }
            if(count>2){
                res = arrs[i];
                break;
            }
        }
        return res;
    }
}
