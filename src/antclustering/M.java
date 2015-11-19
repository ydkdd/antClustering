
package antclustering;

public class M {
    /********************************/
    /* -1, 0, 1            -1,-1,-1 */
    /* -1  ,  1             0  ,  0 */
    /* -1, 0, 1             1, 1, 1 */
    /********************************/
    static final int[][] move_x = {{ 1, 1,-1, 0, 1},
                                   {-1, 1,-1, 0, 1},
                                   {-1,-1,-1, 0, 1},
                                   { 0, 1, 1, 0, 1},
                                   {-1, 0,-1,-1, 0},
                                   {-1, 0, 1, 1, 1},
                                   {-1, 0, 1,-1, 1},
                                   {-1, 0, 1,-1,-1},
                                   {-1, 0, 1,-1, 1,-1, 0, 1}};
    static final int[][] move_y = {{-1, 0, 1, 1, 1},
                                   { 0, 0, 1, 1, 1},
                                   {-1, 0, 1, 1, 1},
                                   {-1,-1, 0, 1, 1},
                                   {-1,-1, 0, 1, 1},
                                   {-1,-1,-1, 0, 1},
                                   {-1,-1,-1, 0, 0},
                                   {-1,-1,-1, 0, 1},
                                   {-1,-1,-1, 0, 0, 1, 1, 1}};
    /**************************************/
    /*-2,-1, 0, 1, 2        -2,-2,-2,-2,-2*/
    /*-2,-1, 0, 1, 2        -1,-1,-1,-1,-1*/
    /*-2,-1, ,  1, 2         0, 0, ,  0, 0*/
    /*-2,-1, 0, 1, 2         1, 1, 1, 1, 1*/
    /*-2,-1, 0, 1, 2         2, 2, 2, 2, 2*/
    /**************************************/
    
    static final int[][] move_X = {{ 2, 1, 2, 1, 2,-1, 0, 1, 2,-2,-1, 0, 1, 2},
                                   {-2,-1, 1, 2,-2,-1, 0, 1, 2,-2,-1, 0, 1, 2},
                                   {-2,-2,-1,-2,-1,-2,-1, 0, 1,-2,-1, 0, 1, 2},
                                   { 0, 1, 2, 0, 1, 2, 1, 2, 0, 1, 2, 0, 1, 2},
                                   {-2,-1, 0,-2,-1, 0,-2,-1,-2,-1, 0,-2,-1, 0},
                                   {-2,-1, 0, 1, 2,-1, 0, 1, 2, 1, 2, 1, 2, 2},
                                   {-2,-1, 0, 1, 2,-2,-1, 0, 1, 2,-2,-1, 1, 2},
                                   {-2,-1, 0, 1, 2,-2,-1, 0, 1,-2,-1,-2,-1,-2},
                                   {-2,-1, 0, 1, 2,-2,-1, 0, 1, 2,-2,-1, 1, 2,-2,-1, 0, 1, 2,-2,-1, 0, 1, 2}};
    static final int[][] move_Y = {{-2,-1,-1, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                                   { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                                   {-2,-1,-1, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2},
                                   {-2,-2,-2,-1,-1,-1, 0, 0, 1, 1, 1, 2, 2, 2},
                                   {-2,-2,-2,-1,-1,-1, 0, 0, 1, 1, 1, 2, 2, 2},
                                   {-2,-2,-2,-2,-2,-1,-1,-1,-1, 0, 0, 1, 1, 2},
                                   {-2,-2,-2,-2,-2,-1,-1,-1,-1,-1, 0, 0, 0, 0},
                                   {-2,-2,-2,-2,-2,-1,-1,-1,-1, 0, 0, 1, 1, 2},
                                   {-2,-2,-2,-2,-2,-1,-1,-1,-1,-1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2},};
}