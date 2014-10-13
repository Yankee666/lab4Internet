#include<stdio.h>
#include<stdlib.h>
#define MaxSize 100000
#define RADIX_10 10    //��������
#define KEYNUM_31 10     //�ؼ��ָ���������Ϊ����λ��

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
*�������ƣ�GetNumInPos
*����˵����num һ����������
*          pos ��ʾҪ��õ����εĵ�posλ����
*˵����    �ҵ�num�Ĵӵ͵��ߵĵ�posλ������
*********************************************************/
int GetNumInPos(int num,int pos)
{
    int temp = 1;
    for (int i = 0; i < pos - 1; i++)
        temp *= 10;

    return (num / temp) % 10;
}

/********************************************************
*�������ƣ�RadixSort
*����˵����pDataArray �������飻
*          iDataNumΪ�������ݸ���
*˵����    ��������
*********************************************************/
void RadixSort(int A[], int n)
{
    int *radixArrays[RADIX_10];    //�ֱ�Ϊ0~9�����пռ�
    for (int i = 0; i < 10; i++)
    {
        radixArrays[i] = (int *)malloc(sizeof(int) * (n + 1));
        radixArrays[i][0] = 0;    //indexΪ0����¼�������ݵĸ���
    }

    for (int pos = 1; pos <= KEYNUM_31; pos++)    //�Ӹ�λ��ʼ��31λ
    {
        for (int i = 0; i < n; i++)    //�������
        {
            int num = GetNumInPos(A[i], pos);
            int index = ++radixArrays[num][0];
            radixArrays[num][index] = A[i];
        }

        for (int i = 0, j =0; i < RADIX_10; i++)    //�ռ�
        {
            for (int k = 1; k <= radixArrays[i][0]; k++)
                A[j++] = radixArrays[i][k];
            radixArrays[i][0] = 0;    //��λ
        }
    }
}
