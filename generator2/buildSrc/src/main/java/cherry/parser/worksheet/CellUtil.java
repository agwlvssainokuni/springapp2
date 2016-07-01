/*
 * Copyright 2014 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.parser.worksheet;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

import org.apache.poi.ss.usermodel.Cell;

public class CellUtil {

	public static String getCellValueAsString(Cell cell) {
		switch (cell.getCellType()) {
		case CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case CELL_TYPE_NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case CELL_TYPE_FORMULA:
			switch (cell.getCachedFormulaResultType()) {
			case CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case CELL_TYPE_NUMERIC:
				return String.valueOf((int) cell.getNumericCellValue());
			default:
				return null;
			}
		default:
			return null;
		}
	}

}
