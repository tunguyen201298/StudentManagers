
-- ----------------------------
-- Table structure for Classes
-- ----------------------------
DROP TABLE [dbo].[Classes]
GO
CREATE TABLE [dbo].[Classes] (
[id] int NOT NULL IDENTITY(1,1) ,
[class_name] nvarchar(100) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Classes]', RESEED, 6)
GO

-- ----------------------------
-- Records of Classes
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Classes] ON
GO
INSERT INTO [dbo].[Classes] ([id], [class_name]) VALUES (N'1', N'Cơ sở dữ liệu')
GO
GO
INSERT INTO [dbo].[Classes] ([id], [class_name]) VALUES (N'2', N'Lập trình Java')
GO
GO
INSERT INTO [dbo].[Classes] ([id], [class_name]) VALUES (N'3', N'Lập trình PHP')
GO
GO
INSERT INTO [dbo].[Classes] ([id], [class_name]) VALUES (N'4', N'Lập trình C#')
GO
GO
INSERT INTO [dbo].[Classes] ([id], [class_name]) VALUES (N'5', N'Lập trình hướng đối tượng')
GO
GO
INSERT INTO [dbo].[Classes] ([id], [class_name]) VALUES (N'6', N'L?p h?c abc')
GO
GO
SET IDENTITY_INSERT [dbo].[Classes] OFF
GO

-- ----------------------------
-- Table structure for ClassStudent
-- ----------------------------
DROP TABLE [dbo].[ClassStudent]
GO
CREATE TABLE [dbo].[ClassStudent] (
[id] int NULL ,
[class_id] int NULL ,
[student_id] int NULL 
)


GO

-- ----------------------------
-- Records of ClassStudent
-- ----------------------------
INSERT INTO [dbo].[ClassStudent] ([id], [class_id], [student_id]) VALUES (null, N'2', N'1')
GO
GO

-- ----------------------------
-- Table structure for DayOff
-- ----------------------------
DROP TABLE [dbo].[DayOff]
GO
CREATE TABLE [dbo].[DayOff] (
[id] int NOT NULL IDENTITY(1,1) ,
[student_id] int NOT NULL ,
[day_off] date NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[DayOff]', RESEED, 1001)
GO

-- ----------------------------
-- Records of DayOff
-- ----------------------------
SET IDENTITY_INSERT [dbo].[DayOff] ON
GO
INSERT INTO [dbo].[DayOff] ([id], [student_id], [day_off]) VALUES (N'2', N'1', N'2019-10-23')
GO
GO
INSERT INTO [dbo].[DayOff] ([id], [student_id], [day_off]) VALUES (N'3', N'5', N'2019-10-15')
GO
GO
INSERT INTO [dbo].[DayOff] ([id], [student_id], [day_off]) VALUES (N'4', N'4', N'2019-10-31')
GO
GO
SET IDENTITY_INSERT [dbo].[DayOff] OFF
GO

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE [dbo].[Student]
GO
CREATE TABLE [dbo].[Student] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] nvarchar(100) NOT NULL ,
[age] int NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Student]', RESEED, 6)
GO

-- ----------------------------
-- Records of Student
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Student] ON
GO
INSERT INTO [dbo].[Student] ([id], [name], [age]) VALUES (N'1', N'Nguyễn Văn Tú', N'20')
GO
GO
INSERT INTO [dbo].[Student] ([id], [name], [age]) VALUES (N'2', N'Trần Văn Phúc', N'22')
GO
GO
INSERT INTO [dbo].[Student] ([id], [name], [age]) VALUES (N'3', N'Đăng Tấn Trường', N'18')
GO
GO
INSERT INTO [dbo].[Student] ([id], [name], [age]) VALUES (N'4', N'Võ Thị Hồng 19', N'21')
GO
GO
INSERT INTO [dbo].[Student] ([id], [name], [age]) VALUES (N'5', N'Lâm Văn Dung ', N'23')
GO
GO
SET IDENTITY_INSERT [dbo].[Student] OFF
GO

-- ----------------------------
-- Indexes structure for table Classes
-- ----------------------------
CREATE INDEX [index_class_name] ON [dbo].[Classes]
([class_name] ASC) 
GO

-- ----------------------------
-- Triggers structure for table Classes
-- ----------------------------
DROP TRIGGER [dbo].[DeleteClass]
GO
CREATE TRIGGER [dbo].[DeleteClass]
ON [dbo].[Classes]
AFTER DELETE
AS
BEGIN
	DELETE s FROM ClassStudent s INNER JOIN deleted ON s.class_id = deleted.id WHERE s.class_id = deleted.id 
END

GO

-- ----------------------------
-- Indexes structure for table Student
-- ----------------------------
CREATE INDEX [index_name] ON [dbo].[Student]
([name] ASC) 
GO

-- ----------------------------
-- Triggers structure for table Student
-- ----------------------------
DROP TRIGGER [dbo].[DeleteStudent]
GO
CREATE TRIGGER [dbo].[DeleteStudent]
ON [dbo].[Student]
AFTER DELETE
AS
BEGIN
	DELETE s FROM ClassStudent s INNER JOIN deleted ON s.student_id = deleted.id WHERE s.student_id = deleted.id 
  DELETE do FROM DayOff do INNER JOIN deleted ON do.student_id = deleted.id WHERE do.student_id = deleted.id 
END

GO
