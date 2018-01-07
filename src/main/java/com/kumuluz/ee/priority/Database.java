/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static List<Priority> priorities = new ArrayList<>(
            Arrays.asList(
                    new Priority("1", 1, 1, 1, 9.99f),
                    new Priority("2", 2, 3, 2, 5.99f),
                    new Priority("3", 1, 2, 8, 3.99f),
                    new Priority("4", 10, 10, 10, 0.00f)
            )
    );



    public static List<Priority> getPriorities() {
        return priorities;
    }

    public static Priority getPriority(String id) {

        for (Priority priority : priorities) {
            if (priority.getId().equals(id))
                return priority;
        }

        return null;
    }


    public static void addPriority(Priority priority) {
        priorities.add(priority);
    }

    public static void deletePriority(String id) {
        for (Priority priority : priorities) {
            if (priority.getId().equals(id)) {
                priorities.remove(priority);
                break;
            }
        }
    }

    public static int comparePriorities(String id1, String id2) {

        Priority p1 = null, p2 = null;

        for (Priority priority : priorities) {
            if (priority.getId().equals(id1)) {
                p1 = priority;
            }
            if (priority.getId().equals(id2)) {
                p2 = priority;
            }
        }

        if (p1 == null || p2 == null) {
            return -999;
        }

        if (p1.getLevel1() == p2.getLevel1()) {
            if (p1.getLevel2() == p2.getLevel2()) {
                if (p1.getLevel3() == p2.getLevel3()) {
                    return 0;
                } else if (p1.getLevel3() < p2.getLevel3()) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (p1.getLevel2() < p2.getLevel2()) {
                return -1;
            } else {
                return 1;
            }
        } else if (p1.getLevel1() < p2.getLevel1()) {
            return -1;
        } else {
            return 1;
        }
    }

}
