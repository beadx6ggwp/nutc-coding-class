import java.util.*;

class card {
    // '\u2663', '\u2666', '\u2665', '\u2660'
    //static String[] img = new String[] { "\u2663", "\u2666", "\u2665", "\u2660" };
    static char[] img = new char[] { 0x05, 0x04, 0x03, 0x06 };
    static String[] card_num = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    static int[] cards = new int[52];

    public static void main(String[] args) {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println("發牌:");

        // 初始化牌組， +1 去配合IndexToCard塞選陣列空格
        for (int i = 0; i < cards.length; i++)
            cards[i] = i + 1;
        //RandomArr(cards);

        int playerNum = 4;
        int[][] players = new int[playerNum][13];
        int count = 0, every = 52 / playerNum, mod = 52 % playerNum;
        // 先均勻發牌
        for (int i = 0; i < playerNum; i++) {
            for (int j = 0; j < every; j++) {
                players[i][j] = cards[count++];
            }
        }
        // 再把沒發完的發出去
        for (int i = 0; i < mod; i++) {
            players[i][every] = cards[count++];
        }

        // Show
        for (int i = 0; i < players.length; i++) {
            System.out.printf("第%s組牌", i);
            for (int j = 0; j < players[0].length; j++) {
                System.out.printf("%5s", IndexToCard(players[i][j]));
            }
            System.out.println();
        }
    }

    public static String IndexToCard(int index) {
        if (index == 0)
            return "";
        index--;
        int type = index / 13;
        int num = index % 13;
        // System.out.printf("%c%d", img[type], num);
        return String.format("%s%-2s", img[type], card_num[num]);
    }

    public static void RandomArr(int[] arr) {
        int lan = arr.length;
        int count = lan, next = 0, swap = 0;
        for (int i = 0; i < lan; i++) {
            next = ((int) (Math.random() * lan)) % count;
            swap = arr[count - 1];
            arr[count - 1] = arr[next];
            arr[next] = swap;
            count--;
        }
        // System.out.println(Arrays.toString(arr));
    }
}