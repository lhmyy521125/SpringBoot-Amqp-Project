USE [rabbitmq_db]
GO
/****** Object:  Table [dbo].[th_message]    Script Date: 04/30/2019 15:19:41 ******/
ALTER TABLE [dbo].[th_message] DROP CONSTRAINT [DF_th_message_status]
GO
ALTER TABLE [dbo].[th_message] DROP CONSTRAINT [DF_th_message_retry_count]
GO
ALTER TABLE [dbo].[th_message] DROP CONSTRAINT [DF_th_message_create_time]
GO
DROP TABLE [dbo].[th_message]
GO
/****** Object:  Table [dbo].[th_order]    Script Date: 04/30/2019 15:19:41 ******/
DROP TABLE [dbo].[th_order]
GO
/****** Object:  Table [dbo].[th_order]    Script Date: 04/30/2019 15:19:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[th_order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[message_id] [nvarchar](50) NULL,
 CONSTRAINT [PK_th_order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'th_order', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单唯一ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'th_order', @level2type=N'COLUMN',@level2name=N'message_id'
GO
SET IDENTITY_INSERT [dbo].[th_order] ON
INSERT [dbo].[th_order] ([id], [name], [message_id]) VALUES (1, N'084bbeb0880d4d139035c6a66b9ad4d3 : 订单消息', N'084bbeb0880d4d139035c6a66b9ad4d3')
INSERT [dbo].[th_order] ([id], [name], [message_id]) VALUES (2, N'b94a91513e4f426ba15f428a6991eba7 : 订单消息', N'b94a91513e4f426ba15f428a6991eba7')
INSERT [dbo].[th_order] ([id], [name], [message_id]) VALUES (3, N'b94a91513e4f426ba15f428a6991eb11 : 订单消息', N'b94a91513e4f426ba15f428a6991eb11')
SET IDENTITY_INSERT [dbo].[th_order] OFF
/****** Object:  Table [dbo].[th_message]    Script Date: 04/30/2019 15:19:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[th_message](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[message_id] [nvarchar](50) NOT NULL,
	[status] [int] NULL CONSTRAINT [DF_th_message_status]  DEFAULT ((0)),
	[retry_count] [int] NULL CONSTRAINT [DF_th_message_retry_count]  DEFAULT ((0)),
	[create_time] [datetime] NULL CONSTRAINT [DF_th_message_create_time]  DEFAULT (getdate()),
	[update_time] [datetime] NULL,
 CONSTRAINT [PK_th_message] PRIMARY KEY CLUSTERED 
(
	[message_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'消息的唯一ID 与订单表message_id关联' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'th_message', @level2type=N'COLUMN',@level2name=N'message_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发送状态 0发送中 1成功 2失败' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'th_message', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'重试次数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'th_message', @level2type=N'COLUMN',@level2name=N'retry_count'
GO
SET IDENTITY_INSERT [dbo].[th_message] ON
INSERT [dbo].[th_message] ([id], [message_id], [status], [retry_count], [create_time], [update_time]) VALUES (1, N'084bbeb0880d4d139035c6a66b9ad4d3', 1, 0, CAST(0x0000AA3B00C360B6 AS DateTime), CAST(0x0000AA3B00C36242 AS DateTime))
INSERT [dbo].[th_message] ([id], [message_id], [status], [retry_count], [create_time], [update_time]) VALUES (3, N'b94a91513e4f426ba15f428a6991eb11', 2, 5, CAST(0x0000AA3B0115E3C3 AS DateTime), NULL)
INSERT [dbo].[th_message] ([id], [message_id], [status], [retry_count], [create_time], [update_time]) VALUES (2, N'b94a91513e4f426ba15f428a6991eba7', 1, 0, CAST(0x0000AA3B0114C51D AS DateTime), CAST(0x0000AA3B0114C565 AS DateTime))
SET IDENTITY_INSERT [dbo].[th_message] OFF
