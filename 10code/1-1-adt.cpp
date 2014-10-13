#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define maxsize 100000
typedef int position;
typedef struct {
    int data;
}Elementtype;
typedef struct{
    Elementtype elements[maxsize];
    int n;
}HEAP;
void Initialize(HEAP *H);
void Insert(HEAP *H,int x);
Elementtype DeleteMax(HEAP *H);
bool HeapEmpty(HEAP *H);
bool HeapFull(HEAP *H);
void PrintHeap(HEAP *H);
int main(){
    HEAP *H;
    int n,i,data;
    clock_t start,end;
    Elementtype max;
    H = new HEAP;
    Initialize(H);
    printf("数据规模\n");
    scanf("%d",&n);
    start = clock();
    for(i = 1;i<=n;i++){
        data = rand()%n;
        Insert(H,data);
    }
    //PrintHeap(H);
    while(H->n>0){
        max = DeleteMax(H);
        //printf(" %d ",max.data);
    }
    end = clock();
    printf("\n耗时：%d ms",end - start);
    return 0;
}
void Initialize(HEAP *H){
    H->n = 0;
}
void Insert(HEAP *H,int x){
    int i;
    if(!HeapFull(H)){
        i = H->n + 1;
        while((i!=1)&&(x > H->elements[i/2].data)){
            H->elements[i] = H->elements[i/2];
            i=i/2;
        }
        H->elements[i].data = x;
    }
    H->n++;
}
Elementtype DeleteMax(HEAP *H){
    int parent = 1,child = 2,i =0;
    Elementtype element,temp;
    if(!HeapEmpty(H)){
        element = H->elements[1];//max
        temp = H->elements[H->n--];
        while(child<=H->n){
            if((child<H->n)&&(H->elements[child].data<H->elements[child+1].data))
                child++;//寻找较大儿子编号2or3
            if(temp.data>H->elements[child].data)break;
            H->elements[parent]= H->elements[child];
            H->elements[child].data = 0;
            parent = child;
            child = child *2;
        }
        H->elements[parent] = temp;

        H->elements[++H->n].data = 0;
        H->n--;
        return element;
    }
}
bool HeapEmpty(HEAP *H){
    return(!H->n);
}
bool HeapFull(HEAP *H){
    if(H->n == maxsize-1)return 1;
    else return 0;
}
void PrintHeap(HEAP *H){
    int i ;
    //printf("No.%d is %d,its lchild is %d and its rchild is %d\n",i,H->elements[i].data,H->elements[1].data,H->elements[2].data);
    for(i = 1;i<H->n;i++){
      printf("No.%d is %d,its lchild is %d and its rchild is %d\n",i,H->elements[i].data,H->elements[2*i].data,H->elements[2*i + 1].data);
      if(2*i>maxsize||2*i+1>maxsize)break;
    }
}
