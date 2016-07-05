-- NAME: getNumberOfWorkday
-- PARAM:
--   String    name
--   LocalDate from
--   LocalDate to
SELECT
	COUNT(H0.dt)
FROM
	dayoff_master H0
WHERE
	H0.name = :name
	AND
	H0.dt BETWEEN :from AND :to
;

-- NAME: getNextWorkday
-- PARAM:
--   String    name
--   LocalDate from
--   int       numberOfWorkday
SELECT
	MIN(D.dt)
FROM
	(SELECT
		A.d*10+B.d AS n,
		ADD_DAYS(:from, A.d*10+B.d) AS dt
	FROM digit A, digit B
	) D
	LEFT OUTER JOIN dayoff_master H0
	ON
		H0.name = :name
		AND
		H0.dt BETWEEN :from AND D.dt
WHERE
	NOT EXISTS (
		SELECT 1 FROM dayoff_master H1
		WHERE
			H1.name = :name
			AND
			H1.dt = D.dt
	)
GROUP BY
	D.n
HAVING
	COUNT(H0.dt) = D.n - :numberOfWorkday + 1
;
