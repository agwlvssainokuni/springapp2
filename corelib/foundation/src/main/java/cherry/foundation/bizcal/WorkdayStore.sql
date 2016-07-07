-- NAME: getNumberOfWorkday
-- PARAM:
--   String    name
--   LocalDate from
--   LocalDate to
SELECT
	COUNT(DISTINCT H0.dt)
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
	ADD_DAYS(:from, D.nm)
FROM
	(SELECT A.d*10+B.d AS nm FROM digit A, digit B) D
WHERE
	NOT EXISTS (
		SELECT 1
		FROM
			dayoff_master H0
		WHERE
			H0.name = :name
			AND
			H0.dt = ADD_DAYS(:from, D.nm)
	)
	AND
	(
		SELECT COUNT(DISTINCT H0.dt)
		FROM
			dayoff_master H0
		WHERE
			H0.name = :name
			AND
			H0.dt BETWEEN :from AND ADD_DAYS(:from, D.nm)
	) = D.nm - :numberOfWorkday + 1
;
