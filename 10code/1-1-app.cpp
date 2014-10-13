#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define maxsize 100
typedef int position;
typedef struct {
    char item[10];
    double data;//price
}Elementtype;
typedef struct{
    Elementtype elements[maxsize];
    int n;
}HEAP;
void Initialize(HEAP *H);
void Insert(HEAP *H,char item[],int price);
Elementtype DeleteMax(HEAP *H);
bool HeapEmpty(HEAP *H);
bool HeapFull(HEAP *H);
void PrintHeap(HEAP *H);
int main(){
    HEAP *H;
    int n,stop;
    char item[10];
    double price;
    Elementtype max1,max2;
    H = new HEAP;
    Initialize(H);
    printf("'1' to start or continue and '-1'to stop\n");
    scanf("%d",&stop);
    while(stop>0){
        printf("Input the Fruit\n");
        scanf("%s",&item);
        printf("Price\n");
        scanf("%lf",&price);
        Insert(H,item,price);
        printf("'1' to start or continue and '-1'to stop\n");
        scanf("%d",&stop);
    }
   //PrintHeap(H);
   printf("Down-Range is\n");
   while(!HeapEmpty(H)){
    max1 = DeleteMax(H);
    printf("%s's price is %.3lf\n",max1.item,max1.data);
   }
   getchar;
   return 0;
}
void Initialize(HEAP *H){
    H->n = 0;
}
void Insert(HEAP *H,char item[],int price){
    int i;
    if(!HeapFull(H)){
        i = H->n + 1;
        while((i!=1)&&(price > H->elements[i/2].data)){
            H->elements[i] = H->elements[i/2];
            i=i/2;
        }
        printf("price is %d\n",price);
        H->elements[i].data = price;
        strcpy(H->elements[i].item,item);
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
                child++;//Ñ°ÕÒ½Ï´ó¶ù×Ó±àºÅ2or3
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
    for(i = 1;i<maxsize;i++){
      if(H->elements[i].data!=0)
      printf("%s's price is  %.3lf,its lchild is %s and its rchild is %s\n",H->elements[i].item,H->elements[i].data,H->elements[2*i].item,H->elements[2*i + 1].item);
      if(2*i>maxsize||2*i+1>maxsize)break;
    }
}
