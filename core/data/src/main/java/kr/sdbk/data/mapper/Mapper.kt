package kr.sdbk.data.mapper

interface Mapper<M, D> {
    fun M.fromModel(): D
    fun D.toModel(): M
}