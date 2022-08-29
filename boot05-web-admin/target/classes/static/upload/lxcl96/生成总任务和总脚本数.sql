-- 总任务数 985
SELECT
	*
FROM
	tbl_moni_scrt_task
WHERE
	TASK_CODE IS NULL
OR TASK_CODE = "";

-- 总脚本数（包含系统自带） 1263
SELECT
	*
FROM
	tbl_moni_scrt
WHERE
	id NOT IN (
		SELECT
			id
		FROM
			tbl_moni_scrt
		WHERE
			IS_HIDE IS NOT NULL
	);

-- 总脚本数（不包含系统自带） 1210
SELECT
	*
FROM
	tbl_moni_scrt
WHERE
	id NOT IN (
		SELECT
			id
		FROM
			tbl_moni_scrt
		WHERE
			IS_HIDE IS NOT NULL
	)
AND SCRIPT_PATH != "/upload/inspect/script/";