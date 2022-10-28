import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class sort {
    public void swap(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    //冒泡排序
    public void bubble(int[] nums){
        int n=nums.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                if(nums[j+1]<nums[j])
                    swap(nums,j,j+1);
            }
        }
    }

    //快排
    public void quickSort(int[]nums,int left,int right){
        int i=left,j=right;
        int mid=nums[i];
        while(i<=j){
            while (nums[i]<mid) i++;
            while (nums[j]>mid) j--;
            if(i<=j){
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        if(left<j) quickSort(nums,left,j);
        if(i<right) quickSort(nums,i,right);
    }

    //堆排序
    public void heapSort(int[] nums){
        int length=nums.length;
        //先初始化位为大根堆
        for(int i=length/2-1;i>=0;i--){
            adjustHeap(nums,i,length);
        }
        for(int i=length-1;i>0;i--){//将大根堆最大的放在后面
            swap(nums,0,i);
            adjustHeap(nums,0,i);
        }
    }
    public void adjustHeap(int[] nums,int root,int length){
        int leftChild=2*root+1; //左孩子节点
        int rightChild=2*root+2; //右孩子节点
        int largestNode=root;
        //在左右孩子与父节点中找出最大节点并交换
        if(leftChild<length&&nums[leftChild]>nums[largestNode]){
            largestNode=leftChild;
        }
        if(rightChild<length&&nums[rightChild]>nums[largestNode]){
            largestNode=rightChild;
        }
        if(largestNode!=root){
            swap(nums,root,largestNode);
            //保证每个子树都是大根堆
            adjustHeap(nums,largestNode,length);
        }
    }

    //选择排序
    public void selectSort(int[] nums){
        int n=nums.length;
        for(int i=0;i<n;i++){
            int min=i;
            for(int j=i;j<n;j++){
                if(nums[min]>nums[j]){
                    min=j;
                }
            }
            swap(nums,i,min);
        }
    }

    //插入排序
    public void insertSort(int[] nums){
        int n=nums.length;
        for(int i=1;i<n;i++){
            for(int j=i;j>0&&nums[j-1]>nums[j];j--){
                swap(nums,j,j-1);
            }
        }
    }

    //归并排序
    public int[] mergeSort(int[] nums,int l,int h){
        if(l==h)
            return new int[]{nums[l]};
        int mid=(l+h)/2;
        int[] leftArray=mergeSort(nums,l,mid);
        int[] rightArray=mergeSort(nums,mid+1,h);
        int[] newArr=new int[leftArray.length+rightArray.length];

        int m=0,i=0,j=0;
        while(i<leftArray.length&&j<rightArray.length){
            newArr[m++]=leftArray[i]<rightArray[j]?leftArray[i++]:rightArray[j++];
        }
        while(i<leftArray.length)
            newArr[m++]=leftArray[i++];
        while(j<rightArray.length)
            newArr[m++]=rightArray[j++];
        return newArr;
    }

    public static void main(String[] args) {
        sort sort=new sort();
        int[] nums={1,5,3,7,6,4,10,8};
        //sort.bubble(nums);
        //sort.quickSort(nums,0,nums.length-1);
        //sort.heapSort(nums);
        //sort.selectSort(nums);
        //sort.insertSort(nums);
        nums=sort.mergeSort(nums,0,nums.length-1);
        for(int i:nums){
            System.out.print(i+"-");
        }
    }
}
