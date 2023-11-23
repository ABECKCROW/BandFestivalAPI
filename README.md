# 出演アーティスト管理API

## ■概要
- 音楽フェスに出演するバンドの情報を管理できる。
- リクエスト日の日付に応じてレスポンスするバンドをフィルターする。
- バンド、出演公開日ともに登録、修正、削除ができる。
  -  下記は実施予定
  - メンバー情報もjoinして取得できるようにする。
  - 登録、修正、削除もできるようにする。

## ■主な使用技術
- バックエンド：Java, Spring Boot
- その他：MySQL, Docker
  - 下記は実施予定
  - 自動テスト
  - CI (CheckStyle, 自動テストを実行)
  - AWSデプロイ

## ■アプリケーション概略図
![概略図 drawio](https://github.com/ABECKCROW/BandFestivalAPI/assets/136610341/9c6ef76c-0bc9-4043-b9a3-3d713a56f5e0)

## ■アプリケーション機能一覧

<details>
<summary>■動作確認(Get)①bandsテーブルよりidとband_nameを取得する。</summary>
<pre>
curl --location 'http://localhost:8080/bands/all/names'
</pre>
<img width="1245" alt="スクリーンショット 2023-08-27 16 42 10" src="https://github.com/ABECKCROW/lecture09/assets/136610341/335674c4-233c-4e86-bb3f-c51624c7d768">
</details>

<details>
<summary>■動作確認(Get)②act_Announcement_Dateが今日以前の①をReadする。</summary>
<pre>
curl --location 'http://localhost:8080/bands/announced/names'
</pre>
<img width="1243" alt="スクリーンショット 2023-08-27 16 40 14" src="https://github.com/ABECKCROW/lecture09/assets/136610341/50fed30b-47f7-4f7c-903e-174dcaae3cde">
</details>

<details>
<summary>■動作確認(Create)</summary>
<pre>
curl --location 'localhost:8080/bands/create' \--header 'Content-Type: application/json' \--data '{"bandName": "Official髭男dism","actAnnouncementDate": "2023-12-01T00:00:00.000Z"}'
</pre>
<img width="1247" alt="スクリーンショット 2023-09-08 17 54 16" src="https://github.com/ABECKCROW/lecture09/assets/136610341/0206c233-932c-411e-a589-bf21fb76919f">
<img width="1247" alt="スクリーンショット 2023-09-08 17 56 34" src="https://github.com/ABECKCROW/lecture09/assets/136610341/3da45275-6ef4-4ba8-bf21-7aa02edbda9d">
</details>

<details>
<summary>■動作確認(Patch)</summary>
<pre>
curl --location --request PATCH 'localhost:8080/bands/update/9' \--header 'Content-Type: application/json' \--data '{"bandName": "ひげだん","actAnnouncementDate": "2023-12-01T00:00:00.000Z"}'
</pre>
<img width="1244" alt="スクリーンショット 2023-09-08 18 01 45" src="https://github.com/ABECKCROW/lecture09/assets/136610341/a8c74850-feab-436c-9d74-35b1d296dc75">
<img width="1246" alt="スクリーンショット 2023-09-08 18 02 49" src="https://github.com/ABECKCROW/lecture09/assets/136610341/e3ebc90c-3df1-4f26-9d3b-8cc105af407e">
</details>

<details>
<summary>■動作確認(Delete)</summary>
<pre>
curl --location --request DELETE 'http://localhost:8080/bands/delete/9'
</pre>
<img width="1244" alt="スクリーンショット 2023-09-08 18 03 49" src="https://github.com/ABECKCROW/lecture09/assets/136610341/cb66fea9-ffcf-4f5f-92dc-2f1b7a38ce82">
<img width="1241" alt="スクリーンショット 2023-09-08 18 04 29" src="https://github.com/ABECKCROW/lecture09/assets/136610341/f3a2643e-247b-418a-8fcd-131319745c0a">
</details>

## ■DBテーブル
### ER図
<img width="674" alt="ER図(band現状)" src="https://github.com/ABECKCROW/BandFestivalAPI/assets/136610341/626a63f9-fa79-4aa4-820f-9fa1e4c833bd">

<details>
<summary>bandsテーブル(詳細)</summary>
<table>
  <thead>
    <tr>
      <th>id</th>
      <th>band_name</th>
      <th>act_Announcement_Date</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>ASIAN KUNG-FU GENERATION</td>
      <td>2023-09-01</td>
    </tr>
    <tr>
      <td>2</td>
      <td>Rhythmic Toy World</td>
      <td>2023-10-01</td>
    </tr>
    <tr>
      <td>3</td>
      <td>UNISON SQUARE GARDEN</td>
      <td>2023-11-01</td>
    </tr>
  </tbody>
</table>
</details>

<details>
<summary>membersテーブル(詳細)</summary>
<table>
  <thead>
    <tr>
      <th>id</th>
      <th>member_name</th>
      <th>part</th>
      <th>band_id</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>Gotoh</td>
      <td>Gt&Vo</td>
      <td>1</td>
    </tr>
    <tr>
      <td>2</td>
      <td>Kita</td>
      <td>Gt</td>
      <td>1</td>
    </tr>
    <tr>
      <td>3</td>
      <td>Yamada</td>
      <td>Ba</td>
      <td>1</td>
    </tr>
    <tr>
      <td>4</td>
      <td>Izichi</td>
      <td>Drum</td>
      <td>1</td>
    </tr>
    <tr>
      <td>5</td>
      <td>Uchida</td>
      <td>Gt&Vo</td>
      <td>2</td>
    </tr>
    <tr>
      <td>6</td>
      <td>Kishi</td>
      <td>Gt</td>
      <td>2</td>
    </tr>
    <tr>
      <td>7</td>
      <td>Sudo</td>
      <td>Ba</td>
      <td>2</td>
    </tr>
    <tr>
      <td>8</td>
      <td>Isomura</td>
      <td>Drum</td>
      <td>2</td>
    </tr>
  </tbody>
</table>
</details>

## ■今後の展望
### ER図
<img width="671" alt="ER図(band展望)" src="https://github.com/ABECKCROW/BandFestivalAPI/assets/136610341/3ab8e949-62b0-4e46-add7-92e85f0ff291">

### ■お客さん側の利用想定
- 出演の発表がされたバンドを取得する。
- 出演発表がされていないバンドは取得できない。
- 出演バンドの発表日にメールが届く。

### ■運営側の利用想定
- 出演バンドの発表前日にリマインドメールが来る。

### ■やることリスト
- [ ] ガントチャート作成
- [ ] 仕様書作成
- [ ] memberテーブルのCRUD処理
- [ ] performance_scheduleテーブルの作成とCRUD処理
- [ ] デプロイ
- [ ] インフラ構成図の作成
- [ ] フロントエンド
- [ ] 管理者とユーザーを分ける(Spring Security)
- [ ] 出演バンド公開前日と当日にメール送信機能
- [ ] バンドの登録時にメンバー人数分の登録処理をまとめたい。(トランザクション) 
