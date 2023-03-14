package cn.kahvia;

import cn.kahvia.test1_2.Triangle;

public class Main {
    static void strongBoundary(){
        Triangle triangle=new Triangle();
        int min_l=0;
        int min=1;
        int min_h=2;
        int normal=50;
        int max_l=99;
        int max=100;
        int max_h=101;
        int counter=0;
        int[] common={min_l,min,min_h,max_l,max,max_h};
        int[] test=new int[3];

        System.out.println(normal+","+normal+","+normal+","+triangle.classify(normal,normal,normal));
        counter++;
        for(int i=0;i<3;i++){
            test[0]=test[1]=test[2]=normal;
            for (int j=0;j<common.length;j++){
                test[i]=common[j];
                int a=test[0],b=test[1],c=test[2];
                System.out.println(a+","+b+","+c+","+triangle.classify(a,b,c));
                counter++;
            }
        }
        System.out.println("counter:"+counter);
    }

    static void commonWorst(){
        Triangle triangle=new Triangle();
        int min=1;
        int min_h=2;
        int normal=50;
        int max_l=99;
        int max=100;
        int counter=0;
        int[] common={min,min_h,normal,max_l,max};
        int[] test=new int[3];

        for(int i=0;i<common.length;i++){
            test[0]=common[i];
            for (int j=0;j<common.length;j++){
                test[1]=common[j];
                for (int k=0;k<common.length;k++){
                    test[2]=common[k];
                    int a=test[0],b=test[1],c=test[2];
                    System.out.println(a+","+b+","+c+","+triangle.classify(a,b,c));
                    counter++;
                }
            }

        }
        System.out.println("counter:"+counter);
    }

    static void strongWorst(){
        Triangle triangle=new Triangle();
        int min_l=0;
        int min=1;
        int min_h=2;
        int normal=50;
        int max_l=99;
        int max=100;
        int max_h=101;
        int counter=0;
        int[] common={min_l,min,min_h,normal,max_l,max,max_h};
        int[] test=new int[3];

        for(int i=0;i<common.length;i++){
            test[0]=common[i];
            for (int j=0;j<common.length;j++){
                test[1]=common[j];
                for (int k=0;k<common.length;k++){
                    test[2]=common[k];
                    int a=test[0],b=test[1],c=test[2];
                    System.out.println(a+","+b+","+c+","+triangle.classify(a,b,c));
                    counter++;
                }
            }

        }
        System.out.println("counter:"+counter);
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        strongBoundary();
    }


}