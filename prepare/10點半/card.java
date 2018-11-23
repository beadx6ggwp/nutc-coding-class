import java.util.*;

class card {
    // '\u2663', '\u2666', '\u2665', '\u2660'
    // static String[] img = new String[] { "\u2663", "\u2666", "\u2665", "\u2660"
    // };
    static char[] img = new char[] { 0x05, 0x04, 0x03, 0x06 };
    static String[] card_num = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    static int[] cards = new int[52];

    public static void main(String[] args) {
        System.out.println("===========十點半===========");
        do {
            Game();
            System.out.println("還要玩嗎?(y / n)");
        } while (System.console().readLine().equals("y"));
    }

    public static void Game() {
        System.out.println("玩家回合\n發牌:");

        // 初始化牌組， +1 去配合IndexToCard塞選陣列空格
        for (int i = 0; i < cards.length; i++)
            cards[i] = i;
        RandomArr(cards);

        int card_count = 0;
        int nowCard = 0;

        Player player = new Player();
        Player ai = new Player();

        // 玩家抽牌
        do {
            nowCard = cards[card_count++];
            player.AddCard(nowCard);
            System.out.println("當前牌組 : " + ShowPlayerCards(player));

            if ((player.point = GetCardsPoint(player)) > 105) {
                player.flag = 1;
                System.out.printf("你的點數:%d.%d, GG\n", player.point / 10, player.point % 10);
                return;
            }

            System.out.println("你還要牌嗎?(y / n)");
        } while (System.console().readLine().equals("y"));
        System.out.println("--------------------------------");
        System.out.println("你的牌組 : " + ShowPlayerCards(player));

        // 電腦抽牌
        System.out.println("================================");
        System.out.println("莊家回合:");

        double n = 0;
        do {
            nowCard = cards[card_count++];
            ai.AddCard(nowCard);
            // System.out.println("當前牌組 : " + ShowPlayerCards(ai));

            if ((ai.point = GetCardsPoint(ai)) > 105) {
                ai.flag = 1;
                System.out.printf("點數:%d.%d, GG\n", ai.point / 10, ai.point % 10);
                break;
            }
            if (ai.point <= 50)
                n = 1;
            else
                n = 1 - (double) ai.point / 105.0;
        } while (Math.random() > 1 - n);
        System.out.println("莊家牌組 : " + ShowPlayerCards(ai));

        // Check Win
        Player winner = CheckWin(player, ai);
        System.out.println("--------------------------------");
        if(winner == player) System.out.println("你贏了");
        else System.out.println("你輸了");
    }

    // 玩家 莊家
    public static Player CheckWin(Player p, Player a) {
        if(a.point > 105) return p;

        // 過五關10點半，三倍賭金
        if (p.point == 105 && p.tail >= 5)
            return p;

        // 玩家過五關 會贏 莊家10點半沒有過五關
        if (p.tail >= 5 && a.tail < 5)
            return p;

        // 玩家點數大
        if (p.point > a.point)
            return p;

        return a;
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