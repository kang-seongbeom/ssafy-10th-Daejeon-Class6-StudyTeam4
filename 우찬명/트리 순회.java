// 전위순회(prefix)
// 중위순회(infix, inorder traverse)
// 후위순회(postfix) ex)병합정렬

import java.io.*;
import java.util.*;

class Node {
    char data;
    Node left, right;
    public Node(char data, Node left, Node right) {
        this.data=data;
        this.left=left;
        this.right=right;
    }
}
public class Main {
    static Node head = new Node('A',null,null);
    public static void prefix(Node root) {
        if(root==null) return;
        else {
            System.out.print(root.data); // prefix
            prefix(root.left);
            prefix(root.right);
        }
    }
    public static void infix(Node root) {
        if(root==null) return;
        else {
            infix(root.left);
            System.out.print(root.data); // infix
            infix(root.right);
        }
    }
    public static void postfix(Node root) {
        if(root==null) return;
        else {
            postfix(root.left);
            postfix(root.right);
            System.out.print(root.data); // postfix
        }
    }
    public static void insertNode(Node temp, char root, char left, char right) {
        if (temp.data == root) {
            temp.left = (left == '.' ? null : new Node(left,null,null));
            temp.right = (right == '.' ? null : new Node(right,null,null));
        }
        else {
            if(temp.left != null) insertNode(temp.left, root, left, right);
            if(temp.right != null) insertNode(temp.right, root, left, right);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n-1; i++) { // 트리의 node들을 입력받을 때는 재귀가 사용됨
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            insertNode(head, root,left,right);
        }
        prefix(head);
        System.out.println();
        infix(head);
        System.out.println();
        postfix(head);
    }
}
