DROP TABLE CUR
CREATE TABLE [dbo].[CUR](
	[ID] [varchar](255) NOT NULL,
	[CUR] [varchar](255) NULL,
	[DESCRIPTION] [varchar](255) NULL,
	[INWORDDESP1] [varchar](255) NULL,
	[INWORDDESP2] [varchar](255) NULL,
	[ISHOMECUR] [bit] NULL,
	[M1] [numeric](18, 4) NULL,
	[M10] [numeric](18, 4) NULL,
	[M11] [numeric](18, 4) NULL,
	[M12] [numeric](18, 4) NULL,
	[M13] [numeric](18, 4) NULL,
	[M2] [numeric](18, 4) NULL,
	[M3] [numeric](18, 4) NULL,
	[M4] [numeric](18, 4) NULL,
	[M5] [numeric](18, 4) NULL,
	[M6] [numeric](18, 4) NULL,
	[M7] [numeric](18, 4) NULL,
	[M8] [numeric](18, 4) NULL,
	[M9] [numeric](18, 4) NULL,
	[HOMEINWORDDESP1] [varchar](255) NULL,
	[HOMEINWORDDESP2] [varchar](255) NULL,
	[SYMBOL] [varchar](255) NULL,
	[VERSION] [int] NULL,
	[CREATEDDATE] [datetime] NULL,
	[CREATEDUSERID] [varchar](255) NULL,
	[UPDATEDDATE] [datetime] NULL,
	[UPDATEDUSERID] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO CUR (ID,CUR,DESCRIPTION,INWORDDESP1,INWORDDESP2,ISHOMECUR,SYMBOL,VERSION) VALUES ('1','MMK','KYAT','KYAT','PYAR',1,'K',1)

