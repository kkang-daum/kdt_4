import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import '../models/user_info.dart';

class DatabaseHelper {
  //singleton....
  static final DatabaseHelper instance = DatabaseHelper._init();
  static Database? _database;

  DatabaseHelper._init();

  Future<Database> get database async {
    if(_database != null) return _database!;
    //앱을 위한 db 작업 최초 한번..
    _database = await _initDB("user_info.db");
    return _database!;
  }
  Future<Database> _initDB(String filePath) async {
    //각 플랫폼별로.. db 파일이 저장되는 위치가 다르다.. 그 위치 경로를 획득..
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, filePath);//db 저장 경로 + db file
    return await openDatabase(
      path,
      version: 1,
      onCreate: _createDB,//app install 후 최초 한번..
    );
  }

  Future _createDB(Database db, int version) async {
    //app install 후.. 초기 준비.. table create...
    await db.execute('''
      CREATE TABLE user_info (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        email TEXT,
        profileImagePath TEXT
      )
    ''');
  }

  Future<void> insertOrUpdateUser(UserInfo userInfo) async {
    final db = await instance.database;

    await db.delete('user_info');
    await db.insert('user_info', userInfo.toMap());
  }
  Future<UserInfo?> getUser() async {
    final db = await instance.database;
    final maps = await db.query('user_info');
    if(maps.isNotEmpty){
      return UserInfo.fromMap(maps.first);
    }
    return null;
  }

  Future close() async {
    final db = await instance.database;
    db.close();
  }
}