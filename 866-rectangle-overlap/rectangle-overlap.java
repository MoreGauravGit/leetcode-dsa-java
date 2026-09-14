class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // rec1 = [x1, y1, x2, y2]
        // rec2 = [x1, y1, x2, y2]

        // Two rectangles overlap if:
        // left edge of one < right edge of other
        // AND right edge of one > left edge of other
        // AND bottom edge of one < top edge of other
        // AND top edge of one > bottom edge of other

        return rec1[0] < rec2[2] &&  // rec1 left < rec2 right
               rec1[2] > rec2[0] &&  // rec1 right > rec2 left
               rec1[1] < rec2[3] &&  // rec1 bottom < rec2 top
               rec1[3] > rec2[1];    // rec1 top > rec2 bottom
    }
}
