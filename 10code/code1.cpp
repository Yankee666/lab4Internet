#include<stdio.h>
#include<stdlib.h>
#define MaxSize 100
#define INF -32767
void InsertSort(int n,int A[]);
int main(){
    int A[MaxSize] = {};
    int n,i;
    printf("Num Of Datas\n");
    scanf("%d",&n);
    printf("Data\n");
    for(i = 1;i<=n;i++)scanf("%d",&A[i]);
    InsertSort(n,A);
    for(i = 1;i<=n;i++)printf(" %d ",A[i]);
    return 0;
}
void InsertSort(int n,int A[]){//和前边的元素比较，符合就交换位置
    int i,j,temp;
    A[0] = INF;
    for(i = 1;i<=n;i++){
        j = i;
        while(A[j]<A[j-1]){
            temp  = A[j];
            A[j] = A[j-1];
            A[j-1] = temp;
            j=j-1;
        }
    }
}
