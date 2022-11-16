
CREATE TABLE [dbo].[RATEINFO](
	[ID] [char](36) NOT NULL,
	[DENORATE] [nvarchar](500) NULL,
	[EXCHANGERATE] [numeric](18, 4) NULL,
	[LASTMODIFY] [bit] NULL,
	[RDATE] [datetime] NULL,
	[RATETYPE] [varchar](5) NULL,
	[VERSION] [int] NULL,
	[CREATEDDATE] [datetime] NULL,
	[CREATEDUSERID] [char](36) NULL,
	[UPDATEDDATE] [datetime] NULL,
	[UPDATEDUSERID] [char](36) NULL,
	[CUR] [char](36) NULL,
	[TOCUR] [char](36) NULL,
	[rate] [decimal](18, 0) NULL
) ON [PRIMARY]
GO


