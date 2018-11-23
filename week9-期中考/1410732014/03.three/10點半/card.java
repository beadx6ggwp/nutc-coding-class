import java.util.*;

class card {
    // '\u2663', '\u2666', '\u2665', '\u2660'
    // static String[] img = new String[] { "\u2663", "\u2666", "\u2665", "\u2660"
    // };
    static char[] img = new char[] { 0x05, 0x04, 0x03, 0x06 };
    static String[] card_num = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    static int[] cards = new int[52];

    public static void main(String[] args) {
        do {
            System.out.println("===========���I�b===========");
            Game();
            System.out.println("�O�_�A�Ӥ@��?(y / n)");
        } while (System.console().readLine().equals("y"));
    }

    public static void Game() {
        System.out.println("���a�^�X\n�o�P:");
        // ��l�ƵP�աA +1 �h�t�XIndexToCard���}�C�Ů�
        for (int i = 0; i < cards.length; i++)
            cards[i] = i;
        RandomArr(cards);

        int card_count = 0;
        int nowCard = 0;

        Player player = new Player();

        // ���a��P
        do {
            nowCard = cards[card_count++];
            player.AddCard(nowCard);
            player.point = GetCardsPoint(player);
            System.out.print("��e�P�� : " + ShowPlayerCards(player));
            System.out.printf(", ��e�I��:%d.%d\n", player.point / 10, player.point % 10);

            if (player.point > 105) {
                player.flag = 1;
                System.out.printf("�A���I��:%d.%d, GG\n", player.point / 10, player.point % 10);
                return;
            }

            System.out.println("�A�٭n�P��?(y / n)");
        } while (System.console().readLine().equals("y"));
        System.out.println("--------------------------------");
        System.out.println("�A���P�� : " + ShowPlayerCards(player));

        // Check Win
        System.out.println("--------------------------------");
        if (CheckWin(player))
            System.out.println("�AĹ�F");
        else
            System.out.println("�A��F");
    }

    // ���a ���a
    public static boolean CheckWin(Player p) {
        // ���a�L����
        if (p.tail >= 5)
            return true;

        // ���a�I�Ƥj
        if (p.point == 105)
            return true;

        return false;
    }

    public static int GetCardsPoint(Player p) {// 10.5 = 105, 0~51
        int count = 0, temp = 0;
        for (int i = 0; i < p.tail; i++) {
            temp = p.cards[i] % 13;
            if (temp >= 10 && temp <= 12) {
                count += 5;
            } else {
                count += (temp + 1) * 10;
            }
        }
        return count;
    }

    public static String ShowPlayerCards(Player p) {
        String str = "";
        for (int i = 0; i < p.tail; i++) {
            str += IndexToCard(p.cards[i]);
        }
        return str;
    }

    // ------------Tool-----------------------------
    public static String IndexToCard(int index) {
        if (index == 0)
            return "";
        int type = index / 13;
        int num = index % 13;
        // System.out.printf("%c%d", img[type], num);
        return String.format("%s%-3s", img[type], card_num[num]);// "XX---"
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

    public static int RandomInt(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}

class Player {
    public int max = 10;
    public int[] cards;
    public int point = 0;
    public int tail = 0;
    public int flag = 0;

    public Player() {
        cards = new int[max];
    }

    public boolean AddCard(int n) {
        if (tail < max) {
            cards[tail++] = n;
            return true;
        }
        return false;
    }

}