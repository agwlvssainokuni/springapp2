--
-- Copyright 2016 agwlvssainokuni
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- cherry.gradle.task.generator.GenerateCode

-- 並び順 (sort_by)
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '00', 'ID', 0);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '01', '文字列【10】', 1);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '02', '整数【64bit】', 2);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '03', '小数【1桁】', 3);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '04', '小数【3桁】', 4);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '05', '日付', 5);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '06', '時刻', 6);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('sort_by', '07', '日時', 7);
