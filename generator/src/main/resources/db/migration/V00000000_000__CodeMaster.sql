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

-- 性別 (gender_type)
INSERT INTO code_master (name, value, label, sort_order) VALUES ('gender_type', '1', '男性', 0);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('gender_type', '2', '女性', 1);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('gender_type', '9', 'なし', 2);

-- 都道府県コード (pref_cd)
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '01', '北海道', 0);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '02', '青森県', 1);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '03', '岩手県', 2);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '04', '宮城県', 3);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '05', '秋田県', 4);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '06', '山形県', 5);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '07', '福島県', 6);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '08', '茨城県', 7);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '09', '栃木県', 8);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '10', '群馬県', 9);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '11', '埼玉県', 10);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '12', '千葉県', 11);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '13', '東京都', 12);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '14', '神奈川県', 13);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '15', '新潟県', 14);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '16', '富山県', 15);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '17', '石川県', 16);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '18', '福井県', 17);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '19', '山梨県', 18);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '20', '長野県', 19);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '21', '岐阜県', 20);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '22', '静岡県', 21);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '23', '愛知県', 22);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '24', '三重県', 23);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '25', '滋賀県', 24);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '26', '京都府', 25);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '27', '大阪府', 26);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '28', '兵庫県', 27);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '29', '奈良県', 28);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '30', '和歌山県', 29);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '31', '鳥取県', 30);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '32', '島根県', 31);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '33', '岡山県', 32);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '34', '広島県', 33);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '35', '山口県', 34);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '36', '徳島県', 35);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '37', '香川県', 36);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '38', '愛媛県', 37);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '39', '高知県', 38);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '40', '福岡県', 39);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '41', '佐賀県', 40);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '42', '長崎県', 41);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '43', '熊本県', 42);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '44', '大分県', 43);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '45', '宮崎県', 44);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '46', '鹿児島県', 45);
INSERT INTO code_master (name, value, label, sort_order) VALUES ('pref_cd', '47', '沖縄県', 46);
