
CREATE TABLE IF NOT EXISTS role (
    role_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(35) NOT NULL UNIQUE,
    PRIMARY KEY (role_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS users (
    user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(35) NOT NULL UNIQUE,
    password VARCHAR(72) NOT NULL,
    fullname VARCHAR(100) NOT NULL UNIQUE,
    role INT UNSIGNED NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role) REFERENCES role(role_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS trade (
    trade_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    type VARCHAR(35) NOT NULL,
    buyQuantity FLOAT NOT NULL,
    sellQuantity FLOAT NOT NULL,
    buyPrice FLOAT NOT NULL,
    sellPrice FLOAT NOT NULL,
    benchmark VARCHAR(35) NOT NULL,
    tradeDate TIMESTAMP NOT NULL,
    security VARCHAR(35) NOT NULL,
    status VARCHAR(35) NOT NULL,
    trader VARCHAR(35) NOT NULL,
    book VARCHAR(35) NOT NULL,
    creationName VARCHAR(35) NOT NULL,
    creationDate TIMESTAMP NOT NULL,
    revisionName VARCHAR(35) NOT NULL,
    revisionDate TIMESTAMP NOT NULL,
    dealName VARCHAR(35) NOT NULL,
    dealType VARCHAR(35) NOT NULL,
    sourceListId VARCHAR(35) NOT NULL,
    side VARCHAR(35) NOT NULL,
    PRIMARY KEY (trade_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS bidlist (
    bidlist_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    account VARCHAR(50) NOT NULL,
    type VARCHAR(35) NOT NULL,
    bid_quantity FLOAT NOT NULL,
    ask_quantity FLOAT NOT NULL,
    bid FLOAT NOT NULL,
    ask FLOAT NOT NULL,
    benchmark VARCHAR(35) NOT NULL,
    bidListDate TIMESTAMP NOT NULL,
    commentary VARCHAR(35) NOT NULL,
    security VARCHAR(35) NOT NULL,
    status VARCHAR(35) NOT NULL,
    trader VARCHAR(35) NOT NULL,
    book VARCHAR(35) NOT NULL,
    creationName VARCHAR(35) NOT NULL,
    creationDate TIMESTAMP NOT NULL,
    revisionName VARCHAR(35) NOT NULL,
    revisionDate TIMESTAMP NOT NULL,
    dealName VARCHAR(35) NOT NULL,
    dealType VARCHAR(35) NOT NULL,
    sourceListId VARCHAR(35) NOT NULL,
    side VARCHAR(35) NOT NULL,
    PRIMARY KEY (bidlist_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS curvepoint (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    curve_id INT NOT NULL,
    asOfDate TIMESTAMP NOT NULL,
    term FLOAT NOT NULL,
    value FLOAT NOT NULL,
    creationDate TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS rating (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    moodysRating VARCHAR(35) NOT NULL,
    sandPRating VARCHAR(35) NOT NULL,
    fitchRating VARCHAR(35) NOT NULL,
    orderNumber INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS rulename (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(35) NOT NULL,
    description VARCHAR(35) NOT NULL,
    json VARCHAR(35) NOT NULL,
    template VARCHAR(35) NOT NULL,
    sqlStr VARCHAR(35) NOT NULL,
    sqlPart VARCHAR(35) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;
