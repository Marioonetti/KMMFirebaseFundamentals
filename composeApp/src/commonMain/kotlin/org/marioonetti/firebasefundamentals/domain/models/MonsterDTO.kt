package org.marioonetti.firebasefundamentals.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Monster(
    val index: String,
    val name: String,
    val size: String,
    val type: String,
    val alignment: String,
    @SerialName("armor_class") val armorClass: List<ArmorClass>,
    @SerialName("hit_points") val hitPoints: Int,
    @SerialName("hit_dice") val hitDice: String,
    @SerialName("hit_points_roll") val hitPointsRoll: String,
    val speed: Speed,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    val proficiencies: List<Proficiency>,
    @SerialName("damage_vulnerabilities") val damageVulnerabilities: List<String>,
    @SerialName("damage_resistances") val damageResistances: List<String>,
    @SerialName("damage_immunities") val damageImmunities: List<String>,
    @SerialName("condition_immunities") val conditionImmunities: List<String>,
    val senses: Senses,
    val languages: String,
    @SerialName("challenge_rating") val challengeRating: Int,
    @SerialName("proficiency_bonus") val proficiencyBonus: Int,
    val xp: Int,
    @SerialName("special_abilities") val specialAbilities: List<SpecialAbility>,
    val actions: List<Action>,
    @SerialName("legendary_actions") val legendaryActions: List<LegendaryAction>,
    val image: String,
    val url: String,
    @SerialName("updated_at") val updatedAt: String
)

@Serializable
data class ArmorClass(
    val type: String,
    val value: Int
)

@Serializable
data class Speed(
    val walk: String? = null,
    val swim: String? = null
)

@Serializable
data class Proficiency(
    val value: Int,
    val proficiency: ProficiencyDetail
)

@Serializable
data class ProficiencyDetail(
    val index: String,
    val name: String,
    val url: String
)

@Serializable
data class Senses(
    val darkvision: String,
    @SerialName("passive_perception") val passivePerception: Int
)

@Serializable
data class SpecialAbility(
    val name: String,
    val desc: String,
    val dc: DC? = null
)

@Serializable
data class DC(
    @SerialName("dc_type") val dcType: DCType,
    @SerialName("dc_value") val dcValue: Int,
    @SerialName("success_type") val successType: String
)

@Serializable
data class DCType(
    val index: String,
    val name: String,
    val url: String
)

@Serializable
data class Action(
    val name: String,
    val desc: String,
    @SerialName("multiattack_type") val multiattackType: String? = null,
    @SerialName("attack_bonus") val attackBonus: Int? = null,
    val dc: DC? = null,
    val damage: List<Damage>? = null,
    val actions: List<SubAction> = emptyList(),
    val usage: Usage? = null
)

@Serializable
data class Damage(
    @SerialName("damage_type") val damageType: DamageType,
    @SerialName("damage_dice") val damageDice: String
)

@Serializable
data class DamageType(
    val index: String,
    val name: String,
    val url: String
)

@Serializable
data class SubAction(
    @SerialName("action_name") val actionName: String,
    val count: Int,
    val type: String
)

@Serializable
data class Usage(
    val type: String,
    val times: Int
)

@Serializable
data class LegendaryAction(
    val name: String,
    val desc: String,
    @SerialName("attack_bonus") val attackBonus: Int? = null,
    val damage: List<Damage>? = null
)