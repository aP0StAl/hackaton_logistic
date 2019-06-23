package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommisVoyageurTaskService {
    private final DistanceService distanceService;
    private static double INF = 1e9 + 7;

    public ArrayList<GeoPoint> getPath(GeoPoint start, List<GeoPoint> points, List<GeoPoint> points2) {
        points.addAll(points2);

        Map<GeoPoint, Map<GeoPoint, Double>> distanceMap = distanceService.getDistanceMap(points);

        int n = points.size();
        double[][]dp = new double[1 << n][n];
        int[][]path = new int[1 << n][n];

        for (int i = 0; i < (1 << n); ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = INF;
                path[i][j] = -1;
            }
        }

        dp[1][0] = 0;
        for (int mask = 0; mask < (1 << n); ++mask) {
            for (int i = 0; i < n; ++i) {
                if (dp[mask][i] == INF)
                    continue;
                for (int j = 0; j < n; ++j) {
                    if ((mask & (1 << j)) == 0 &&
                            dp[mask | (1 << j)][j] > dp[mask][i] + distanceMap.get(points.get(i)).get(points.get(j))) {
                        dp[mask | (1 << j)][j] = dp[mask][i] + distanceMap.get(points.get(i)).get(points.get(j));
                        path[mask | (1 << j)][j] = i;
                    }
                }
            }
        }

        double minPath = INF;
        int last, cur = -1;
        for (int i = 0; i < n; ++i) {
            if (dp[(1 << n) - 1][i] < minPath) {
                minPath = dp[(1 << n) - 1][i];
                cur = i;
            }
        }

        ArrayList<GeoPoint> answerPath = new ArrayList<GeoPoint>();
        int mask = (1 << n) - 1;
        while (cur != -1) {
            answerPath.add(points.get(cur));
            last = cur;
            cur = path[mask][cur];
            mask = mask & (~(1 << last));
        }

        assert(answerPath.size() != n);

        return answerPath;
    }
}
