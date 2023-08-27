# lecture09(MyBatisでReadの実装)

### 概要
* GETリクエストを2種類実装  
①bandsテーブルよりact_Announcement_Dateを除いたidとband_nameをReadする。  
②act_Announcement_Dateが今日以前の①をReadする。

### 想定
フェスイベントで出演バンドを順番に発表する。  
発表する日が決まっていてリクエストした今日時点で発表済みのバンドをレスポンスする。  
ここまでは実装済み。  
  
### 今後の展望
出演バンドの情報もレスポンスしていきたい。  
出演ステージごとにタイムテーブルを組んでグループ分けし、カラムとレコード登録  
  
### DB登録のテーブル

* bandsテーブル
  
| id | band_name | act_Announcement_Date |
|------------|------------|------------|
| 1      | ASIAN KUNG-FU GENERATION      | 2023-08-26      |
| 2      | Rhythmic Toy World      | 2023-08-27      |
| 3      | UNISON SQUARE GARDEN      | 2023-09-01      |

* membersテーブル　※今回使用なし。
  
| id | member_name | part  | band_id |
|----|-------------|-------|---------|
|  1 | Gotoh       | Gt&Vo |       1 |
|  2 | Kita        | Gt    |       1 |
|  3 | Yamada      | Ba    |       1 |
|  4 | Izichi      | Drum  |       1 |
|  5 | Uchida      | Gt&Vo |       2 |
|  6 | Kishi       | Gt    |       2 |
|  7 | Sudo        | Ba    |       2 |
|  8 | Isomura     | Drum  |       2 |
|  9 | Saitoh      | Gt&Vo |       3 |





### 動作確認
