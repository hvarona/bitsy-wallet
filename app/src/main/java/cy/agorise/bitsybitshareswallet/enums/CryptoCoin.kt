package cy.agorise.bitsybitshareswallet.enums

import org.bitcoinj.core.NetworkParameters
import java.io.Serializable
import java.util.ArrayList

enum class CryptoCoin private constructor(
    cryptoNet: CryptoNet,
    label: String,
    precision: Int,
    coinNumber: Int,
    parameters: NetworkParameters?
) :
    Serializable {
    BITCOIN(CryptoNet.BITCOIN, "BTC", 8, 0, NetworkParameters.fromID(NetworkParameters.ID_MAINNET)!!),
    BITCOIN_TEST(CryptoNet.BITCOIN_TEST, "BTC", 8, 1, NetworkParameters.fromID(NetworkParameters.ID_TESTNET)!!),
    LITECOIN(CryptoNet.LITECOIN, "LTC", 8, 2, null),
    DASH(CryptoNet.DASH, "DASH", 8, 5, null),
    DOGECOIN(CryptoNet.DOGECOIN, "DOGE", 8, 3, null),
    BITSHARES(CryptoNet.BITSHARES, "BTS", 5, 0, null);

    var cryptoNet: CryptoNet
        protected set
    var label: String
        protected set
    var precision: Int = 0
        protected set
    var coinNumber: Int = 0
        protected set
    var parameters: NetworkParameters
        protected set

    init {
        this.cryptoNet = cryptoNet
        this.label = label
        this.precision = precision
        this.coinNumber = coinNumber
        this.parameters = parameters!!

    }

    companion object {

        fun getByCryptoNet(cryptoNet: CryptoNet): List<CryptoCoin> {
            val result = ArrayList<CryptoCoin>()

            for (nextCryptoCoin in CryptoCoin.values()) {
                if (nextCryptoCoin.cryptoNet.equals(cryptoNet)) {
                    result.add(nextCryptoCoin)
                }
            }

            return result
        }
    }

}