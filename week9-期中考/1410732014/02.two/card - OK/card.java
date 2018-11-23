import java.util.*;

class card {
    // '\u2663', '\u2666', '\u2665', '\u2660'
    // static String[] img = new String[] { "\u2663", "\u2666", "\u2665", "\u2660"
    // };
    static char[] img = new char[] { 0x05, 0x04, 0x03, 0x06 };
    static String[] card_num = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    static int[] cards = new int[52];

    public static void main(String[] args) {
        System.out.println("發牌:");

        // 初始化牌組， +1 去配合IndexToCard塞選陣列空格
        for (int i = 0; i < cards.length; i++)
            cards[i] = i + 1;
        RandomArr(cards);

        int playerNum = 4;
        int[][] players = new int[playerNum][13];
        int count = 0, every = 52 / playerNum, mod = 52 % playerNum;

        int win = 2;
        int start = 13 * win;
        int end = start + 13; // <
        int point, point2, temp;
        for (int i = start; i < end; i++) {
            point = (cards[i] - 1) % 13 + 1;
            if (point % 2 != 0) {
                for (int j = 0; j < cards.length; j++) {
                    if (j >= start && j < end)
                        continue;
                    point2 = (cards[j] - 1) % 13 + 1;
                    if (point2 % 2 == 0) {                        
                        temp = cards[i];
                        cards[i] = cards[j];
                        cards[j] = temp;
                        break;
                    }
                }
            }
        }

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

        for (int i = 0; i < playerNum; i++)
            Sort(players[i]);
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

    public static void Sort(int[] arr) {
        int t, convertA, convertB;
        for (int i = 0; i < arr.length; i++) {
            // System.out.print((arr[i]-1)%13 + " ");
            for (int j = 0; j < i; j++) {
                convertA = Convert1(arr[i], 4, 13);
                convertB = Convert1(arr[j], 4, 13);
                if (convertA < convertB) {
                    t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    public static int Convert1(int n, int r, int c) {
        n--;
        return (n % c * r + n / c) + 1;
    }

    public static int Convert2(int n, int r, int c) {
        n--;
        return (n / r + (n % r * c)) + 1;
    }
}