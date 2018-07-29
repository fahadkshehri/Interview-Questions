/**
 * @author  Fahad Alshehri
 * @Date  7/2/2018.
 * Independent Study
 * Heap Interview Questions
 */

import java.util.*;

public class Heap {

    public static void main(String[] args) throws Exception {

    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - (k - 1)];

        if (nums.length == 0) return new int[0];

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0, j = 0, l = nums.length; i < l; i++) {
            int head = i - (k - 1);
            if (head >= 0) {
                //remove out of range
                if (queue.peek() != null && queue.peek() < head)
                    queue.poll();
            }
            while (queue.peekLast() != null && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k - 1)
                result[j++] = nums[queue.peek()];
        }

        return result;
    }

    public int candy(int[] ratings) {
        if(ratings.length == 1) return 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(ratings[o1], ratings[o2]));
        for(int i = 0; i < ratings.length; i ++){
            pq.offer(i);
        }
        int[] count = new int[ratings.length];
        while (!pq.isEmpty()){
            int index = pq.poll();
            if(index - 1 < 0){
                if(ratings[index + 1] == ratings[index]){
                    count[index] = 1;
                } else{
                    count[index] = count[index + 1] + 1;
                }
            } else if(index + 1 >= ratings.length) {
                if(ratings[index - 1] == ratings[index]){
                    count[index] = 1;
                } else{
                    count[index] = count[index - 1] + 1;
                }
            } else{
                if((ratings[index - 1] == ratings[index]) && (ratings[index + 1] == ratings[index])){
                    count[index] = 1;
                } else{
                    if(((ratings[index - 1] == ratings[index]) && (ratings[index + 1] > ratings[index]))
                            || ((ratings[index + 1] == ratings[index]) && (ratings[index - 1] > ratings[index]))){
                        count[index] = 1;
                    } else if(((ratings[index - 1] == ratings[index]) && (ratings[index + 1] < ratings[index]))){
                        count[index] = count[index + 1] + 1;
                    } else if(((ratings[index + 1] == ratings[index]) && (ratings[index - 1] < ratings[index]))){
                        count[index] = count[index - 1] + 1;
                    }
                    else {
                        if(count[index - 1] > count[index + 1]){
                            count[index] = count[index - 1] + 1;
                        } else {
                            count[index] = count[index + 1] + 1;
                        }
                    }
                }
            }
        }
        int result = 0;
        for(int c : count){
            result += c;
        }
        return result;
    }
    public List<int[]> getSkyline(int[][] buildings) {
        PriorityQueue<Rectangle> pq = new PriorityQueue<>(Comparator.comparing(Rectangle::getH)
                .reversed()
                .thenComparing(Rectangle::getX1)); //order by height, if height is same then, order by left most starting edge.
        List<int[]> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int[] p : buildings) {
            set.add(p[0]);
            set.add(p[1]);
        }
        List<Integer> points = new ArrayList<>();
        points.addAll(set);
        points.sort(Integer::compare);

        for (int i = 0, j = 0, l = points.size(); i < l; i++) {
            int curr = points.get(i);

            for (int k = j; k < buildings.length; k++) { //add all the rectangles that begin at this point
                int[] rectangle = buildings[k];
                if (rectangle[0] == curr) {
                    pq.offer(new Rectangle(rectangle[0], rectangle[1], rectangle[2]));
                } else if (rectangle[0] > curr) {
                    j = k;
                    break;
                }
            }
            int max = Integer.MIN_VALUE;
            while (!pq.isEmpty()) { // remove all the rectangles that end at this point
                if (pq.peek().getX2() == curr) {
                    Rectangle top = pq.poll();
                    max = Math.max(max, top.getH());
                } else if (pq.peek().getX2() < curr) {
                    pq.poll();
                } else {
                    break;
                }
            }
            if (pq.isEmpty()) {
                result.add(makeNewPoint(curr, 0)); //This is the last rectangle after this there is a gap of at least one unit
            } else {
                if (max > pq.peek().getH()) {
                    result.add(makeNewPoint(curr, pq.peek().getH())); //one of the larger rectangle's right edge intersects with a smaller one
                } else if (max < pq.peek().getH() && pq.peek().getX1() == curr) {
                    result.add(makeNewPoint(curr, pq.peek().getH())); //new larger rectangle begins at this point
                }
            }
        }
        return result;
    }

    private int[] makeNewPoint(int x, int y) {
        int[] point = new int[2];
        point[0] = x;
        point[1] = y;
        return point;
    }

    class Rectangle {
        private int x1, x2, h;

        Rectangle(int x1, int x2, int h) {
            this.x1 = x1;
            this.x2 = x2;
            this.h = h;
        }

        public int getH() {
            return h;
        }

        public int getX2() {
            return x2;
        }

        public int getX1() {
            return x1;
        }
    }
}
