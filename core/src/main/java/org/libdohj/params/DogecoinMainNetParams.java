/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.libdohj.params;

import org.bitcoinj.core.Sha256Hash;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main Dogecoin production network on which people trade
 * goods and services.
 */
public class DogecoinMainNetParams extends AbstractDogecoinParams {
    public static final int MAINNET_MAJORITY_WINDOW = 2000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 1900;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 1500;
    protected static final int DIFFICULTY_CHANGE_TARGET = 100000;

    public DogecoinMainNetParams() {
        super(DIFFICULTY_CHANGE_TARGET);
        // not in this case would be 188 ... since 60 + 128 = 188 .......in this case its my own weird thing that still works as 30 + 128 for 158 from the standard desktop qt wallet from dogecoin actually using the proper value of address_prefix + 128(still works for radiocoin-qt as 158 as a customized value to decode the private key from address_prefix 60 "R")
        dumpedPrivateKeyHeader = 12; //This is always addressHeader + 128
        addressHeader = 68;
        p2shHeader = 115;
        port = 8235;
        packetMagic = 0xf9d9cdc3;
        //segwitAddressHrp = "radc";
        // Note that while BIP44 makes HD wallets chain-agnostic, for legacy
        // reasons we use a Doge-specific header for main net. At some point
        // we'll add independent headers for BIP32 legacy and BIP44.
        bip32HeaderP2PKHpub = 0x0488b21e; //The 4 byte header that serializes in base58 to "dgub".
        bip32HeaderP2PKHpriv =  0x0488ade4; //The 4 byte header that serializes in base58 to "dgpv".
        genesisBlock.setDifficultyTarget(0x1e7fffffL);
        genesisBlock.setTime(1524198159L);
        genesisBlock.setNonce(261378L);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 100000;
        spendableCoinbaseDepth = 10;

        // Note this is an SHA256 hash, not a Scrypt hash. Scrypt hashes are only
        // used in difficulty calculations.
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("6be1ade2619d1402571996e436b726c8b0bd72f10fdcae10cff5acd369118626"),
                genesisHash);

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(300000, Sha256Hash.wrap("05afa15162271b7b03d950b04df8f6a8429c696d53601e7163df4fc5514564f5"));

        
        
  // ?? risky business readding dogecoin dns seeds here to attempt experimentation with peer to peer "bloom filter bit" that might be missing from radiocoin dns seed points for android wallet support (in experimentation to match protocol header)
        dnsSeeds = new String[] {
                "radiopool.me",
               24.101.139.92",
"79.117.52.19",
"149.248.61.164",
"118.25.3.242",
"122.155.205.25",
"62.171.141.242",
"91.185.215.213",
"86.120.103.206",


            };
    }
    private static DogecoinMainNetParams instance;
    public static synchronized DogecoinMainNetParams get() {
        if (instance == null) {
            instance = new DogecoinMainNetParams();
        }
        return instance;
    }

    @Override
    public boolean allowMinDifficultyBlocks() {
        return false;
    }

    @Override
    public String getPaymentProtocolId() {
        // TODO: CHANGE THIS
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }

    @Override
    public boolean isTestNet() {
        return false;
    }
}
