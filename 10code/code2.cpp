#include<stdio.h>
#include<stdlib.h>
#define MaxSize 100000
#define RADIX_10 10    //整形排序
#define KEYNUM_31 10     //关键字个数，这里为整形位数

void RadixSort(int A[], int n);
int GetNumInPos(int num,int pos);
int main()
{
    int A[MaxSize] = {},i,n;
    printf("Num\n");
    scanf("%d",&n);
    printf("Data\n");
    for(i = 0;i<=n;i++)A[i] = rand()%n;
    RadixSort(A,n);
    for(i = 0; i<=n; i++)printf(" %d ",A[i]);
    return 0;
}
/********************************************************
*函数名称：GetNumInPos
*参数说明：num 一个整形数据
*          pos 表示要获得的整形的第pos位数据
*说明：    找到num的从低到高的第pos位的数据
*********************************************************/
int GetNumInPos(int num,int pos)
{
    int temp = 1;
    for (int i = 0; i < pos - 1; i++)
        temp *= 10;

    return (num / temp) % 10;
}

/********************************************************
*函数名称：RadixSort
*参数说明：pDataArray 无序数组；
*          iDataNum为无序数据个数
*说明：    基数排序
*********************************************************/
void RadixSort(int A[], int n)
{
    int *radixArrays[RADIX_10];    //分别为0~9的序列空间
    for (int i = 0; i < 10; i++)
    {
        radixArrays[i] = (int *)malloc(sizeof(int) * (n + 1));
        radixArrays[i][0] = 0;    //index为0处记录这组数据的个数
    }

    for (int pos = 1; pos <= KEYNUM_31; pos++)    //从个位开始到31位
    {
        for (int i = 0; i < n; i++)    //分配过程
        {
            int num = GetNumInPos(A[i], pos);
            int index = ++radixArrays[num][0];
            radixArrays[num][index] = A[i];
        }

        for (int i = 0, j =0; i < RADIX_10; i++)    //收集
        {
            for (int k = 1; k <= radixArrays[i][0]; k++)
                A[j++] = radixArrays[i][k];
            radixArrays[i][0] = 0;    //复位
        }
    }
}
