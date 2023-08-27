# lecture09(MyBatisでReadの実装)

### 概要
* GETリクエストを2種類実装  
①bandsテーブルよりact_Announcement_Dateを除いたidとband_nameをReadする。  
②act_Announcement_Dateが今日以前の①をReadする。

### 想定
フェスイベントで出演バンドを順番に発表する。  
発表する日が決まっていてリクエストした今日時点で発表済みのバンドをレスポンスする。  
また重複するバンド名を省いて一意のデータとしてレスポンスする。
ここまでは実装済み。  
  
### 今後の展望
最初はフェスを想定していなかったため、ネーミングを見直したい。
出演バンドの詳細情報もレスポンスしていきたい。  
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

### ①bandsテーブルよりact_Announcement_Dateを除いたidとband_nameをReadする。  
```
curl --location 'http://localhost:8080/bands/all/names'
```
<img width="1245" alt="スクリーンショット 2023-08-27 16 42 10" src="https://github.com/ABECKCROW/lecture09/assets/136610341/335674c4-233c-4e86-bb3f-c51624c7d768">

*** 

### ②act_Announcement_Dateが今日以前の①をReadする。
```
curl --location 'http://localhost:8080/bands/announced/names'
```

<img width="1243" alt="スクリーンショット 2023-08-27 16 40 14" src="https://github.com/ABECKCROW/lecture09/assets/136610341/50fed30b-47f7-4f7c-903e-174dcaae3cde">




