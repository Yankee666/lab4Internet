#include<stdio.h>
#define MaxSize 100000
void SelectSort(int n,int A[]);
int main(){
    int A[MaxSize] = {};
    int i,n;
    printf("Num \n");
    scanf("%d",&n);
    printf("Data\n");
    for(i = 1;i<=n;i++)scanf("%d",&A[i]);//position 0 not used
    SelectSort(n,A);
    for(i = 1;i<=n;i++)printf(" %d ",A[i]);
    return 0;
}
void SelectSort(int n,int A[]){
    int i,j,LowIndex,min,temp;
    for(i = 1;i<n;i++){
        LowIndex = i;
        min = A[i];
        for(j = i+1;j<=n;j++)
        if(A[j]<min){
            min = A[j];
            LowIndex = j;
        }
        temp = A[i];
        A[i] = A[LowIndex];
        A[LowIndex] = temp;
    }
}
